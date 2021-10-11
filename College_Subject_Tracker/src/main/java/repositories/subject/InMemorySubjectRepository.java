package repositories.subject;


import models.Subject;
import repositories.InMemoryAbstractRepository;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public final class InMemorySubjectRepository extends InMemoryAbstractRepository implements ISubjectRepository {
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Static variables
	//
	private static final List<Subject> subjects = new CopyOnWriteArrayList<>();
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Main constructor
	//
	public InMemorySubjectRepository() {
		try {
		
		
		}
		catch (Exception err) {
			System.out.println(err.getLocalizedMessage());
		}
	}
	
	
	// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	// Methods
	//
	public final Subject getSubject(UUID uuid) {
		ListIterator<Subject> iterator = subjects.listIterator();
		
		while (iterator.hasNext()) {
			Subject subject = iterator.next();
			if (subject.getUuid().equals(uuid)) return subject;
		}
		
		return null;
	}
	
	@Override
	public final List<Subject> all() {
		if (!(subjects != null && !subjects.isEmpty())) {
			return null;
		}
		
		List<Subject> subjectList = new ArrayList<>();
		subjects.iterator().forEachRemaining(e -> {
			subjectList.add(e);
		});
		
		return subjectList;
	}
	
	@Override
	public final Subject addSubject(Subject subject) {
		subjects.add(subject);
		return subject;
	}
	
	@Override
	public final void deleteSubject(UUID uuid) {
		subjects.iterator().forEachRemaining(e -> {
			if (e.getUuid() == uuid) {
				subjects.remove(e);
			}
		});
	}
	
	@Override
	public final Subject editSubject(Subject subject) {
		if (!(subjects != null && !subjects.isEmpty())) {
			return null;
		}
		
		Subject originalSubject = getSubject(subject.getUuid());
		if (originalSubject == null) {
			return null;
		}
		
		deleteSubject(subject.getUuid());
		addSubject(subject);
		
		return subject;
	}
	
}