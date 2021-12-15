package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // 애플리케이션 로딩 시점에 딱 하나만 필요
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 쓰레드 간의 공유 X, 사용하고 나면 바로 버려야 한다.
        EntityManager entityManager = emf.createEntityManager();
        //JPA의 모든 데이터 변경은 트랜젝션 안에서 실행
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        // save code
        try {
            Team team = new Team();
            team.setName("TeamA");
            entityManager.persist(team);

            Member member = new Member();
            member.setName("MemberA");
            member.setTeam(team);
            entityManager.persist(member);

            Team findTeam = member.getTeam();
            System.out.println("FIND TEAM : " + findTeam.getName()); // 영속성 컨텍스트의 1차 캐시에서 데이터를 조회

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            entityManager.close(); // 내부적으로 DB Connection을 물고 작업을 하기 때문에 작업이 끝나면 반드시 닫아줘야 함
        }

        // modify code
//        try {
//            Member findMember = entityManager.find(Member.class, 1L);
//            findMember.setName("helloJPA");
//
//            List<Member> result = entityManager.createQuery("SELECT m FROM Member as m ", Member.class)
//                    .setFirstResult(0) // Paging 관련 첫번째 불러올 index
//                    .setMaxResults(10) // 마지막 불러 올 index
//                    .getResultList();
//
//            for (Member member : result) {
//                System.out.println("Member Name : " + member.getName());
//            }
//
//            tx.commit(); // 트랜젝션을 커밋하는 시점에서 바뀐 내용이 있다면 UPDATE 쿼리를 실행
//        } catch (Exception e) {
//            tx.rollback();
//        } finally {
//            entityManager.close();
//        }

        emf.close();
    }
}
