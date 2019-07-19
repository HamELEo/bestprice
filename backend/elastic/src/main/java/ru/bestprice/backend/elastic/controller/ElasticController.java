package ru.bestprice.backend.elastic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bestprice.backend.exlibrary.entity.Receipt;

@RestController
public class ElasticController {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @PostMapping(value = "save_qr_code", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveQrCode(@RequestBody Receipt receipt) {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
