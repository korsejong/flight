package com.github.korsejong.flight.domain.history;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CRUDTest {
    @Autowired
    HistoryRepository repository;
    @Test
    public void createTest(){
        String fromLat = "111.11";
        String fromLng = "111.12";
        String toLat = "111.13";
        String toLng = "111.14";
        History history = new History(fromLat,fromLng,toLat,toLng);

        String fromLat2 = "111.11";
        String fromLng2 = "111.12";
        String toLat2 = "111.13";
        String toLng2 = "111.14";
        History history2 = new History();
        history2.setFromLat(fromLat2);
        history2.setFromLat(fromLng2);
        history2.setToLat(toLat2);
        history2.setToLng(toLng2);

        repository.save(history);
        repository.save(history2);

        List<History> savedList = repository.findAll();

        for(History e: savedList){
            System.out.println(e.toString());
        }

        assertThat(savedList, hasSize(2));
    }
    @Test
    public void readTest(){

    }
    @Test
    public void updateTest(){

    }
    @Test
    public void deleteTest(){

    }
}
