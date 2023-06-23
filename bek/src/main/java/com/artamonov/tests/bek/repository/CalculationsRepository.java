package com.artamonov.tests.bek.repository;

import com.artamonov.tests.bek.model.entity.Calculations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface CalculationsRepository extends JpaRepository<Calculations, Long> {
    @Transactional
    @Query(value = "select date_created from calculations where number_one=:numberOne", nativeQuery = true)
    Date findDate(String numberOne);

    @Query(value = "select *\n" +
            "from calculations\n" +
            "where (date_created > :startDate) AND (date_created < :endDate)", nativeQuery = true)
    List<Calculations> findByDate(Date startDate, Date endDate);

    @Query(value = "select *\n" +
            "from calculations\n" +
            "where (number_system_one = :numberSystemOne OR :numberSystemOne=0)\n" +
            "  AND (number_system_two = :numberSystemTwo OR :numberSystemTwo=0)\n" +
            "  AND (operation_name = :operationName OR :operationName='')\n" +
            "  AND (date_created > :startDate) AND (date_created < :endDate)", nativeQuery = true)
    List<Calculations> findByParameters(Integer numberSystemOne, Integer numberSystemTwo, String operationName, Date startDate, Date endDate);


    @Query(value = "select *\n" +
            "from calculations\n" +
            "where (number_system_one = cast(:numberSystemOne as varchar) like '*')\n" +
            "  AND (number_system_two = cast(:numberSystemTwo as varchar) like '*')\n" +
            "  AND (operation_name = :operationName Like '*')\n" +
            "  AND (date_created > :startDate) AND (date_created < :endDate)", nativeQuery = true)
    List<Calculations> findByParametersAnd(Integer numberSystemOne, Integer numberSystemTwo, String operationName, Date startDate, Date endDate);

    @Transactional
    @Modifying
    @Query(value = "delete from calculations", nativeQuery = true)
    void clear();
}
