package com.farabbit.threaddemo.service;

import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

/**
 * @author mengka
 * @Date 2024-06-30 21:06
 */
public interface FileStorageService {

    public void init();

    public Mono<String> save(Mono<FilePart> filePartMono);

}
