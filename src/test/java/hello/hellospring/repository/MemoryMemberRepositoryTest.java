package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트가 끝날 때 마다 메모리를 지워주는 코드가 필요! >>> 테스트는 순서에 의존해서는 안 되기 때문
    // AfterEach >>> 메서드가 실행이 끝날때마다 동작 = @Test로 호출하는 메서드가 끝날때마다 실행
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // Case 1. 직접 출력
        //System.out.println("result = " + (result == member));

        // Case 2. jupiter의 Assertions 사용 >> assertEquals
        //Assertions.assertEquals(member, result);

        // Case 3. assertj의 Assertions 사용 >> assertThat & isEqualTo
        assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName() {

        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findByAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member1.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }

}

// +) 개발할 때 MemoryMemberRepositoryTest.java와 같이 테스트를 먼저 만든 후 개발 >> TDD(Test Driven Development)