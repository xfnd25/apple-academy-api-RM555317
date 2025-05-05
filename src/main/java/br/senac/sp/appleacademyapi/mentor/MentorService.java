package br.senac.sp.appleacademyapi.mentor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MentorService {

    private final MentorRepository repository;

    public MentorService(MentorRepository mentorRepository) {
        this.repository = mentorRepository;
    }

    public List<Mentor> getAll() {
        return repository.findAll();    
    }

    public Mentor create(Mentor mentor) {
        return repository.save(mentor);
    }

    public Mentor getById(UUID id) {
        return findById(id);
    }

    public void delete(UUID id) {
        repository.delete(findById(id));
    }

    public Mentor update(UUID id, Mentor mentorToUpdate) {
        var mentor = findById(id);
        BeanUtils.copyProperties(mentorToUpdate, mentor, "id", "createdAt");
        mentor.setUpdatedAt(LocalDateTime.now());
        return repository.save(mentor);
    }

    private Mentor findById(UUID id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException( HttpStatus.NOT_FOUND ,"Mentor with id " +  id + " not found"));
    }

    
}
