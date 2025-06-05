package board.board_Project.service;

import board.board_Project.domain.Data;
import board.board_Project.repository.DataRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DataServiceTest {

    @Autowired
    private DataService dataService;

    @Test
    void saveAndFindTest() {
        // 데이터 생성
        Data data = new Data();
        data.setTitle("테스트 글");
        data.setName("tester");
        data.setContent("테스트 내용입니다.");

        // 저장
        Data saved = dataService.save(data);

        // 저장된 데이터 아이디로 조회
        Optional<Data> found = dataService.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("테스트 글");

        // 이름으로 조회
        List<Data> listByName = dataService.findByName("tester");
        assertThat(listByName).isNotEmpty();

        System.out.println("저장 및 조회 테스트 성공!");
    }
}
