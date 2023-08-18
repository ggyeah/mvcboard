package com.goodee.mvcboard.service;

import java.io.File;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.goodee.mvcboard.mapper.BoardMapper;
import com.goodee.mvcboard.mapper.BoardfileMapper;
import com.goodee.mvcboard.vo.Board;
import com.goodee.mvcboard.vo.Boardfile;

@Service
@Transactional
public class BoardService {
	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private BoardfileMapper boardfileMapper;
	
	//REST API chart 호출
	public List<Map<String, Object>> getLocalNameList() {
		return boardMapper.selectLocalNameList();
	}
	
	//삭제
	public int removeBoard(int boardNo, String path) {
		//1) 파일데이터를 통해 upload에 있는 파일부터 삭제
		List<Boardfile> fileList = boardfileMapper.getSavefileName(boardNo);
		int fileSize = fileList.size();
		System.out.println("fileList의 크기: " + fileSize);
		for (Boardfile file : fileList) {
			String filePath = path+file.getSaveFilename();
		    File f = new File(filePath);
		    if (f.exists()) {
		    	   if (f.delete()) {
		                System.out.println("파일 삭제 성공: " + filePath);
		            } else {
		                System.out.println("파일 삭제 실패: " + filePath);
		            }
		        } else {
		            System.out.println("파일이 존재하지 않습니다: " + filePath);
		        }
		    }
		//2) boardfile 데이터 삭제
		int row = boardfileMapper.deleteBoardfile(boardNo);
		//3) board 데이터 삭제
		boardMapper.deleteBoard(boardNo);
		return row;
	}
	
	//파일삭제!!
	public int deleteBoardfile(int boardfileNo, int boardNo, String path) {
		//1) 파일데이터를 통해 upload에 있는 파일부터 삭제
		List<Boardfile> fileList = boardfileMapper.getSavefileName2(boardfileNo);
		int fileSize = fileList.size();
		System.out.println("fileList의 크기: " + fileSize);
		for (Boardfile file : fileList) {
			String filePath = path+file.getSaveFilename();
		    File f = new File(filePath);
		    if (f.exists()) {
		    	   if (f.delete()) {
		                System.out.println("파일 삭제 성공: " + filePath);
		            } else {
		                System.out.println("파일 삭제 실패: " + filePath);
		            }
		        } else {
		            System.out.println("파일이 존재하지 않습니다: " + filePath);
		        }
		    }
		//2) boardfile 데이터 삭제
		int row = boardfileMapper.deleteBoardfile2(boardfileNo);
		return row;
	}
	
	//수정
	public int modifyBoard(Board board, String path) {
		// 파일이 넘어왔을때
		if(board.getMultipartFile() !=null) {
		//1)파일 새로 넣기
		//첨부된 파일이 1개 이상 있다면
		List<MultipartFile> insertfileList = board.getMultipartFile();
		if(insertfileList != null && insertfileList.size()>0) {
			int boardNo = board.getBoardNo(); // board를 추가하면서 생긴 board_no값 받아오기
			
			for(MultipartFile mf : insertfileList) { //첨부된 파일의 개수만큼 반복
				if(mf.getSize()>0) {
				Boardfile bf = new Boardfile();
				bf.setBoardNo(boardNo); // 부모키 값
				bf.setOriginFilename(mf.getOriginalFilename()); // 파일원본이름
				bf.setFilesize(mf.getSize()); // 파일사이즈
				bf.setFiletype(mf.getContentType()); //파일타입(MIME)
				// 저장될 파일 이름
				// 확장자
				String ext = mf.getOriginalFilename().substring(mf.getOriginalFilename().lastIndexOf("."));//마지막점의 위치값을 받아와서 자름
				//새로운 이름 + 확장자
				bf.setSaveFilename(UUID.randomUUID().toString().replace("-","") + ext);
				
				//테이블에 저장
				boardfileMapper.insertBoardfile(bf);
				// 파일 저장(저장위치필요 -> path변수)
				// path위치에 저장파일이름으로 빈파일을 생성
				File f = new File(path+bf.getSaveFilename());
				//빈파일에 첨부된 파일의 스트림을 주입한다
				try {
					mf.transferTo(f);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
					
					// 트랜잭션 작동을 위해 예외(try...catch강요하지 않는 예외 ex: RuntimeException) 발생이 필요하다.
					throw new RuntimeException();
				   }
				}
			}
		}
		}
		//2) board 수정
		int row = boardMapper.updateBoard(board);
		return row;
}
	//추가
	public int addBoard(Board board, String path) {
		// board추가
		int row = boardMapper.insertBoard(board);
		//첨부된 파일이 1개 이상 있다면
		List<MultipartFile> fileList = board.getMultipartFile();
		if(row == 1 && fileList != null && fileList.size()>0) {
			int boardNo = board.getBoardNo(); // board를 추가하면서 생긴 board_no값 받아오기
			
			for(MultipartFile mf : fileList) { //첨부된 파일의 개수만큼 반복
				if(mf.getSize()>0) {
				Boardfile bf = new Boardfile();
				bf.setBoardNo(boardNo); // 부모키 값
				bf.setOriginFilename(mf.getOriginalFilename()); // 파일원본이름
				bf.setFilesize(mf.getSize()); // 파일사이즈
				bf.setFiletype(mf.getContentType()); //파일타입(MIME)
				// 저장될 파일 이름
				// 확장자
				String ext = mf.getOriginalFilename().substring(mf.getOriginalFilename().lastIndexOf("."));//마지막점의 위치값을 받아와서 자름
				//새로운 이름 + 확장자
				bf.setSaveFilename(UUID.randomUUID().toString().replace("-","") + ext);
				
				//테이블에 저장
				boardfileMapper.insertBoardfile(bf);
				// 파일 저장(저장위치필요 -> path변수)
				// path위치에 저장파일이름으로 빈파일을 생성
				File f = new File(path+bf.getSaveFilename());
				//빈파일에 첨부된 파일의 스트림을 주입한다
				try {
					mf.transferTo(f);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
					
					// 트랜잭션 작동을 위해 예외(try...catch강요하지 않는 예외 ex: RuntimeException) 발생이 필요하다.
					throw new RuntimeException();
				   }
				}
			}
		}
		return row;
	}
	
	//상세보기
	public  Map<String, Object>  getBoardOne(int boardNo) {
		List<Boardfile> fileList = boardfileMapper.getSavefileName(boardNo);
		Board board = boardMapper.selectBoardOne(boardNo);
		
		 Map<String, Object> boardOne = new HashMap<String, Object>();
		 boardOne.put("fileList", fileList);
		 boardOne.put("board", board);
		
		return boardOne;
	}
	
	//리스트
	public Map<String, Object> getBoardList(int currentPage, int rowPerPage, String localName) {
		
		//service layer 역활1 : controller가 넘겨준 매개값을 dao의 매개값에 맞게 가공
		int beginRow = (currentPage - 1)*rowPerPage;
		
		// 반환값 1
		 List<Map<String, Object>> localNameList = boardMapper.selectLocalNameList();
		 
		 Map<String, Object> paramMap = new HashMap<String, Object>();
		 paramMap.put("beginRow", beginRow);
		 paramMap.put("rowPerPage", rowPerPage);
		 paramMap.put("localName", localName);
		 
		 
		 //반환값2
		 List<Board> boardList = boardMapper.selectBoardListByPage(paramMap);
	    
	    int boardCount = boardMapper.selectBoardCount();
		//service layer 역활2 : dao에서 반환받은 값을 가공하여 controller에 반환 
		int lastPage = boardCount / rowPerPage;
		if(boardCount % rowPerPage != 0) {
			lastPage += 1;
		}
		
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		    resultMap.put("localNameList", localNameList);
		    resultMap.put("boardList", boardList);
		    resultMap.put("lastPage", lastPage);
		   
		 return resultMap;
	}
}
