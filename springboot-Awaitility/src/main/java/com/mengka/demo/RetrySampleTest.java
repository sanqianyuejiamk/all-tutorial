package com.mengka.demo;

import com.mengka.demo.request.PetRequest;
import com.mengka.demo.response.GetPetByIdResponse;
import com.mengka.demo.retry.GenericRetryClient;
import com.mengka.demo.retry.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author mengka
 * @Date 2024-06-09 18:40
 */
public class RetrySampleTest {

    @Test
    public void getPetByIdTest() throws Exception {

        GetPetByIdResponse getPetByIdResponse = new GenericRetryClient<GetPetByIdResponse>()
                .runCallable(() -> new PetRequest().getPetById(8));

        Assert.assertEquals(getPetByIdResponse.getStatusCode(),200);
    }


    @Test
    public void getPetByIdGenericRetryTest() throws Exception {
        GetPetByIdResponse getPetByIdResponse = new GenericRetryClient<GetPetByIdResponse>()
                .runCallable(() -> new PetRequest().getPetById(1));


    }

    @Test
    public void getPetByIdRetryTest() throws Exception {
        GetPetByIdResponse getPetByIdResponse  = new Retry().run(1);
        Assert.assertEquals(getPetByIdResponse.getStatusCode(),200);
    }
}
