package com.ilgusi.notice.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ilgusi.notice.model.service.NoticeService;
import com.ilgusi.notice.model.vo.Notice;

import common.FileNameOverlap;

@Controller
public class NoticeController {
	@Autowired
	private NoticeService service;
	
	//공지사항 목록으로 이동 
	@RequestMapping("/noticeList.do")
	public String noticeList (Model model) {
		ArrayList<Notice> list = service.selectNoticeList();
		model.addAttribute("list", list);
		return "notice/noticeList";
	}
	
	//공지사항 작성
	@RequestMapping("/noticeWriteFrm.do")
	public String noticeWriteFrm () {
		return "notice/noticeWriteFrm";
	}
	
	
	@RequestMapping("/insertNotice.do")
	public String insertNotice(MultipartHttpServletRequest mRequest, Model model, HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("/");
		String path = root+"upload/notice/";
		System.out.println("파일 경로 : " + path);
		
		MultipartFile file = mRequest.getFile("filename");
		String filename = file.getOriginalFilename();
		String filepath = new FileNameOverlap().rename(path, filename);
		
		byte[] bytes;
		try {
			bytes = file.getBytes();
			File upFile = new File(path+filepath);
			FileOutputStream fos = new FileOutputStream(upFile);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			bos.write(bytes);
			bos.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Notice n= new Notice();
		n.setFilename(filename);
		n.setFilepath(filepath);
		n.setNTitle(request.getParameter("nTitle"));
		n.setNContent(request.getParameter("nContent"));
		int result = service.insertNotice(n);
		if(result > 0 ) {
			model.addAttribute("msg","등록되었습니다.");
		}else {
			model.addAttribute("msg","등록실패.");
		}
		model.addAttribute("loc", "/noticeList.do");
		return "common/msg";
	}
	
	//공지사항 내용 보기
	@RequestMapping("/noticeView.do")
	public String noticeView (int nNo, Model model, Notice n) {
		n.setNNo(nNo);
		n = service.selectNoticeView(nNo);
		model.addAttribute("n", n);
		if(n == null) {
			System.out.println("null");
		}else
			System.out.println(n.getNTitle());
		return "notice/noticeView";
	}
	
}
