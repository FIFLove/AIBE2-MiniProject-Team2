package board.board_Project.service;

import board.board_Project.domain.Data;
import board.board_Project.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DataService {

    private final DataRepository dataRepository;

    @Autowired
    public DataService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public Data save(Data data) {
        data.setCreatedAt(LocalDateTime.now()); // 현재 시간 세팅
        return dataRepository.save(data);
    }

    public Optional<Data> findById(Long id) {
        return dataRepository.findById(id);
    }

    public List<Data> findByName(String name) {
        return dataRepository.findByName(name);
    }

    // 게시글 전체 조회 메서드
    public List<Data> findAll() {
        return dataRepository.findAll();
    }

    // 글 삭제 기능
    public void delete(Long id) {
        dataRepository.deleteById(id);  // DataRepository의 deleteById 호출
    }
}

