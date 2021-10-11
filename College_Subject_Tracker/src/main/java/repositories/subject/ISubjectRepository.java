package repositories.subject;

import models.Subject;

import java.util.List;

public interface ISubjectRepository {
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Abstract methods
	//
	public List<Subject> all();
	
	public Subject getSubject(UUID uuid);
	
	public void deleteSubject(UUID uuid);
	
	public Subject addSubject(Subject subject);
	
	public Subject editSubject(Subject subject);
}
