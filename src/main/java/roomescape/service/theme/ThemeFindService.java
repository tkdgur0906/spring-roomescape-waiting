package roomescape.service.theme;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import roomescape.domain.Theme;
import roomescape.repository.ThemeRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ThemeFindService {

    private static final int START_DAYS_SUBTRACT = 7;
    private static final int END_DAYS_SUBTRACT = 1;
    private static final int RANK_COUNT = 7;

    private final ThemeRepository themeRepository;

    public ThemeFindService(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    public List<Theme> findThemes() {
        return themeRepository.findAll();
    }

    public List<Theme> findThemeRanks() {
        return themeRepository.findPopularThemes(
                LocalDate.now().minusDays(START_DAYS_SUBTRACT),
                LocalDate.now().minusDays(END_DAYS_SUBTRACT),
                PageRequest.of(0, RANK_COUNT)
        );
    }
}
