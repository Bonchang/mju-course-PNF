/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package Components.Middle;

import java.io.IOException;
import Framework.CommonFilterImpl;

public class MiddleFilter extends CommonFilterImpl {
    @Override
    public boolean specificComputationForFilter() throws IOException {
        int byte_read = 0;
        StringBuilder lineBuilder = new StringBuilder();

        while (true) {
            byte_read = in.read();
            if (byte_read == -1) break;

            // 줄바꿈 시 현재 라인 처리
            if (byte_read == '\n') {
                String line = lineBuilder.toString().trim();
                String[] tokens = line.split(" ");

                // 조건: 학번이 2013으로 시작하는 학생만 전달
                if (tokens.length >= 1 && tokens[0].startsWith("2013")) {
                    System.out.println("[MiddleFilter] Passing line: " + line); // 디버깅용 콘솔 출력
                    for (char c : line.toCharArray()) {
                        out.write(c);
                    }
                    out.write('\n');
                }
                lineBuilder.setLength(0); // 다음 라인을 위해 초기화
            } else {
                lineBuilder.append((char) byte_read);
            }
        }
        return true;
    }
}