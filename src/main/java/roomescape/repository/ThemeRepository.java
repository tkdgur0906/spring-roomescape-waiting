package roomescape.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import roomescape.domain.Theme;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {

    @Query("""
            SELECT t 
            FROM Theme t 
            INNER JOIN Reservation r 
            ON t.id = r.theme.id 
            WHERE r.date BETWEEN :dateFrom AND :dateTo 
            GROUP BY t.id 
            ORDER BY COUNT(t.id) DESC
            """)
    List<Theme> findRanksByPeriodAndCount(@Param("dateFrom") LocalDate dateFrom,
                                          @Param("dateTo") LocalDate dateTo,
                                          Pageable rankCount);
}
