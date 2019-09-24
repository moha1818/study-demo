package com.moha.demo.threadpool;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

/**
 * @ClassName LongStream
 * @Author MoHa
 * @Description TODO
 * @Date 2019-08-16 11:37
 **/
public class ALongStream {
    public static void main(String[] args) {

        Instant start = Instant.now();
        LongStream.rangeClosed( 0,11000 )
                //并行流
                .parallel()
                .reduce( 0,Long::sum );
        Instant middle = Instant.now();

        System.out.println("耗费时间"+ Duration.between( start,middle ).toMillis());

        LongStream.rangeClosed( 0,11000 )
                //顺序流
                .sequential()
                .reduce( 0,Long::sum );


        Instant end = Instant.now();
        System.out.println("耗费时间"+ Duration.between( middle,end ).toMillis());

    }
}
