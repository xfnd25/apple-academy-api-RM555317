package br.senac.sp.appleacademyapi.mentor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.senac.sp.appleacademyapi.security.AuthUserRepository;
import jakarta.transaction.Transactional;

@Service
public class MentorService {

    private final MentorRepository repository;
    private final AuthUserRepository authUserRepository;

    public MentorService(MentorRepository mentorRepository, AuthUserRepository authUserRepository) {
        this.repository = mentorRepository;
        this.authUserRepository = authUserRepository;
    }

    public List<Mentor> getAll() {
        return repository.findAll();    
    }

    public MentorResponse create(MentorRequest mentorRequest) {
        var mentor = repository.save(mentorRequest.toMentor());
        var authUser = mentorRequest.toAuthUser(mentor.getId());
        authUserRepository.save(authUser);
        return MentorResponse.from(mentor, authUser);
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
