package com.blog.posts.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.blog.posts.serviceManager.CommentsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



//@Path("/comments")
//@Produces(MediaType.APPLICATION_JSON)
//public class CommentsResource {
//
//	@POST
//	public String addComment(String request) throws JsonProcessingException{
//		ObjectMapper objectMapper = new ObjectMapper();
//		String response;
//		response = objectMapper.writeValueAsString("SUCCESS");
//		try {
//			CommentsService commentsService = new CommentsService();
//			commentsService.addComment(request);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			response = objectMapper.writeValueAsString("FAILURE: Exception occured");
//		}
//		
//		return response;
//		
//	}
//}
