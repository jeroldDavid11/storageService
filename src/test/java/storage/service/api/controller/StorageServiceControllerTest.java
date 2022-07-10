package storage.service.api.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class StorageServiceControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testCreatedirectoryClientError() throws Exception {
		this.mockMvc.perform(post("/api/createdirectory")).andDo(print()).andExpect(status().is4xxClientError())
				.andExpect(content().string(containsString("")));
	}
	
	@Test
	public void testCreatedDirectory() throws Exception 
	{
		mockMvc.perform( MockMvcRequestBuilders
	      .post("/api/createdirectory")
	      .content("{\r\n"
	      		+ "    \"directoryPath\": \"/Test8\"\r\n"
	      		+ "}")
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void testCalculateSize() throws Exception 
	{
		mockMvc.perform( MockMvcRequestBuilders
	      .post("/api/calculateSize")
	      .content("{\r\n"
	      		+ "    \"directoryPath\": \"/Test8\"\r\n"
	      		+ "}")
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testAddFile() throws Exception 
	{
		
		 File f = new File("\\ServiceTest\\Test10\\filename.txt");
         System.out.println(f.isFile()+"  "+f.getName()+f.exists());
         FileInputStream fi1 = new FileInputStream(f);
         MockMultipartFile fstmp = new MockMultipartFile("file", f.getName(), "multipart/form-data",fi1);
         this.mockMvc.perform(MockMvcRequestBuilders.fileUpload("/api/addFile")                
                 .file(fstmp)
                 .param("filePath","//ServiceTest/Test10/"))               
                 .andExpect(status().isOk());
	   		
	}


}
