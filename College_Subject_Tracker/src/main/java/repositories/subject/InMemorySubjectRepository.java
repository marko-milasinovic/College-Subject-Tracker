package repositories.subject;


import core.Configs;
import models.Subject;
import org.apache.commons.io.FileUtils;
import oshi.SystemInfo;
import repositories.InMemoryAbstractRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class InMemorySubjectRepository extends InMemoryAbstractRepository implements ISubjectRepository{
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Static variables
	//
	private static final List<Subject> subjects = new CopyOnWriteArrayList<>();
	

	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Main constructor
	//
	public InMemorySubjectRepository()
	{
		try {
		
			
		}
		catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
	}
	
	
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Methods
	//
	public List<Subject> all() {
		if ( !(subjects != null && !subjects.isEmpty()) ){
			return null;
		}
		
		List<Subject> subjectList = new ArrayList<>();
		subjects.iterator().forEachRemaining(e -> {
			subjectList.add(e);
		});
		
		return Collections.unmodifiableList(subjectList);
		//for easier testing
		//return subjectList;
	}
	
	@Override
	public List<Subject> getSubjects() {
		if ( !(subjects != null && !subjects.isEmpty()) ){
			return null;
		}
		return subjects;
	}
	
	@Override
	public Subject addSubject(Subject subject) {
		subjects.add(subject);
		return subject;
	}
	
	@Override
	public boolean deleteSubject(Integer id) {
		return subjects.remove(id);
	}
	
	
	
	
	
}
//protected static Collection<Path> find(String fileName, String searchDirectory) throws IOException {
//		try (Stream<Path> files = Files.walk(Paths.get(searchDirectory))) {
//			return files.filter(f -> f.getFileName().toString().equals(fileName))
//					.collect(Collectors.toList());
//
//		}
//	}