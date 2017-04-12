//package com.bnrc.sdn.repository;
//
//import java.io.Serializable;
//
//import org.springframework.context.annotation.Profile;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import com.bnrc.sdn.resource.Dp;
//
////@Repository
//public interface DpRepository extends MongoRepository<Dp, String>{
//	//先继承MongoRepository<T, TD>接口，其中T为仓库保存的bean类，TD为该bean的唯一标识的类型，一般为ObjectId。
//	Dp findByDpId(Integer dpId);
//	Dp findByIp(String Ip);
//	
//    @Query("{ 'dpId':?1,'ip': ?1}")  
//     Dp findByDpIdAndIp(Integer dpId,String ip);
//
//}
