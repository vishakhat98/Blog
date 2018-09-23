package com.blog.posts.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
//import com.fasterxml.jackson.databind.ObjectWriter;


import org.bson.Document;

import com.blog.posts.model.PostsDataBean;
import com.blog.posts.serviceManager.CommentsService;
import com.blog.posts.serviceManager.PostsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import javax.ws.rs.core.MediaType;

@Path("/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostsResource {

	@GET
	public String getPosts(){
		PostsService postsService = new PostsService();
		List<Document> serviceResponse = postsService.getPosts();
		String response = null;
		// List<PostsDataBean> response = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			response = objectMapper.writeValueAsString(serviceResponse);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	@POST
	public String createPost(String request) throws IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		@SuppressWarnings("deprecation")
		ObjectReader reader = objectMapper.reader(PostsDataBean.class);
		String writer = null;  
	    Date date = new Date();  
		String returnMsg = objectMapper.writeValueAsString("SUCCESS");
		if(request == null){
			returnMsg = objectMapper.writeValueAsString("FAILURE: No input");
			return returnMsg;
		}
		try {
			PostsDataBean postsDataBean = reader.readValue(request);
				postsDataBean.set_id(postsDataBean.getTitle()+postsDataBean.getAuthor()); //
			postsDataBean.setDate(date);
			writer = objectMapper.writeValueAsString(postsDataBean);
			PostsService postsService = new PostsService();
			 postsService.createPost(writer);	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			returnMsg = objectMapper.writeValueAsString("FAILURE: Exception");
			return returnMsg;
		}
		
		return returnMsg;	
	}
	
	@DELETE
	public String deletePost(String post_id) throws JsonProcessingException{
		PostsService postsService = new PostsService();
		ObjectMapper objectMapper = new ObjectMapper();
		String returnMsg = objectMapper.writeValueAsString("SUCCESS");
		if(post_id == null){
			returnMsg = objectMapper.writeValueAsString("FAILURE: No input");
		}
		try{
			postsService.deletePost(post_id);
		}catch (Exception e) {
			// TODO: handle exception
			returnMsg = objectMapper.writeValueAsString("FAILURE: Exception");
			return returnMsg;
		}
		return returnMsg;
	}
	
	@GET
	@Path("/{author}")
	public String getAllPostsOfAuthor(@PathParam("author") String author) throws JsonProcessingException{
		PostsService postsService = new PostsService();
		ObjectMapper objectMapper = new ObjectMapper();
		String response = null;
		List<Document> posts = new ArrayList<>();
		try {
			posts = postsService.getAllPostsOfAuthor(author);
			response = objectMapper.writeValueAsString(posts);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = objectMapper.writeValueAsString("FAILURE: Exception");
		}
		return response;
		
	}
	
	@Path("/comments")
	@POST
	public String addComment(String request) throws JsonProcessingException{
		ObjectMapper objectMapper = new ObjectMapper();
		String response;
		response = objectMapper.writeValueAsString("SUCCESS");
		try {
			CommentsService commentsService = new CommentsService();
			commentsService.addComment(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = objectMapper.writeValueAsString("FAILURE: Exception occured");
		}
		
		return response;
		
	}
}
