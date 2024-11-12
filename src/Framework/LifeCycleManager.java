package Framework;

import Components.Source.SourceFilter;
import Components.Middle.MiddleFilter;
import Components.AddFilter.AddFilter;

public class LifeCycleManager {
    public static void main(String[] args) {
        try {
            // Courses.txt와 Students.txt를 읽어오기 위한 SourceFilter 인스턴스 생성
            CommonFilter courseSourceFilter = new SourceFilter("Courses.txt");
            CommonFilter studentSourceFilter = new SourceFilter("Students.txt");

            // MiddleFilter는 Courses.txt에서 과목 정보와 선수 과목 정보를 처리
            MiddleFilter middleFilter = new MiddleFilter();

            // AddFilter는 MiddleFilter에서 받은 과목 정보를 사용하여 Students.txt의 학생 정보를 처리
            AddFilter addFilter = new AddFilter(middleFilter);

            // 필터 연결
            // Courses.txt -> MiddleFilter
            courseSourceFilter.connectOutputTo(middleFilter);

            // Students.txt -> AddFilter
            studentSourceFilter.connectOutputTo(addFilter);

            // 스레드 실행
            Thread courseThread = new Thread(courseSourceFilter);
            Thread middleThread = new Thread(middleFilter);
            Thread studentThread = new Thread(studentSourceFilter);
            Thread addThread = new Thread(addFilter);

            // 각각의 스레드를 순차적으로 실행 및 종료 대기
            courseThread.start();
            courseThread.join();  // Courses.txt 처리 완료 후에 MiddleFilter 실행

            middleThread.start();
            middleThread.join();  // MiddleFilter 처리 완료 후에 Students.txt 실행

            studentThread.start();
            studentThread.join();  // Students.txt 처리 완료 후에 AddFilter 실행

            addThread.start();
            addThread.join(); // AddFilter에서 최종 처리가 끝날 때까지 대기

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}