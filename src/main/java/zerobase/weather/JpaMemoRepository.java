package zerobase.weather;

import org.springframework.data.jpa.repository.JpaRepository;
import zerobase.weather.domain.Memo;

public interface JpaMemoRepository extends JpaRepository<Memo, Integer> {
}
