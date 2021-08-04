FROM gradle as builder

WORKDIR /workspace
COPY ./ ./
RUN gradle bootJar

FROM openjdk:11.0.12-jre

ENV LANG en_US.UTF-8
ENV LANGUAGE zh_CN:zh
ENV TZ Asia/Shanghai

COPY --from=builder /workspace/build/libs/*.jar /opt/app.jar
CMD ["java","-jar","-Dfile.encoding=UTF-8","/opt/app.jar"]
