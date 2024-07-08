package com.example.Modelmapper_example;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController 
{
	@Autowired
	ModelMapper modelmapper;
	@Autowired
	StudentRepository srepo;
	@RequestMapping("/test")
	public String test()
	{
		return "test for modelmapper";
	}
	@PostMapping("/save")
	public StudentDTO save(@RequestBody StudentDTO studto)
	{
		Student student=new Student();
		modelmapper.map(studto,student);
		srepo.save(student);
		return studto;
	}
	@GetMapping("/all")
	public List<StudentDTO> alldata()
	{
		return srepo.findAll().stream().map(student->modelmapper.map(student,StudentDTO.class))
				.collect(Collectors.toList());//changing to the list
	}
	@RequestMapping("upd/{id}")
	public StudentDTO update(@RequestBody StudentDTO studto,@PathVariable int id)
	{
		Student s=srepo.findById(id).get();
		s.setAge(studto.getAge());
		s.setCity(studto.getCity());
		s.setName(studto.getName());
		modelmapper.map(s,studto);
		return studto;
	}

}
