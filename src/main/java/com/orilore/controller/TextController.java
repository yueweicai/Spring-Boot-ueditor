package com.orilore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextController {
	@RequestMapping("/getcontent")
	public boolean getContent(String content){
		System.out.println(content);
		return true;
	}
}
