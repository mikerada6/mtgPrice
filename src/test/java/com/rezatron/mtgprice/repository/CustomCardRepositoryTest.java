package com.rezatron.mtgprice.repository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataMongoTest
@ExtendWith( SpringExtension.class )
public
class CustomCardRepositoryTest {
//    @DisplayName( "given object to save" + " when save object using MongoDB template" + " then object is saved" )
//    @Test
//    public
//    void test(
//            @Autowired
//            MongoTemplate mongoTemplate)
//    {
//        // given
//        DBObject objectToSave = BasicDBObjectBuilder.start().add( "key",
//                                                                  "value" ).get();
//
//        // when
//        mongoTemplate.save( objectToSave,
//                            "collection" );
//
//        // then
//        assertThat( mongoTemplate.findAll( DBObject.class,
//                                           "collection" ) ).extracting( "key" ).containsOnly( "value" );
//    }
}
