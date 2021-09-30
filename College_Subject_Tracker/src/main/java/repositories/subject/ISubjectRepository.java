package repositories.subject;

import models.Subject;

import java.util.List;

public interface ISubjectRepository {
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Abstract methods
	//
	public List<Subject> getSubjects();
	public Subject addSubject(Subject subject);
	//public Subject editSubject(Subject subject);
	public boolean deleteSubject(Integer id);
}
