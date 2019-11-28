package com.woowacourse.tecobrary.librarybook.application;

import com.woowacourse.tecobrary.common.model.BookCoverUrl;
import com.woowacourse.tecobrary.common.model.BookInfo;
import com.woowacourse.tecobrary.librarybook.domain.LibraryBook;
import com.woowacourse.tecobrary.librarybook.domain.LibraryBookRepository;
import com.woowacourse.tecobrary.librarybook.exception.DuplicatedLibraryBookException;
import com.woowacourse.tecobrary.librarybook.ui.LibraryBookCreateResponseDto;
import com.woowacourse.tecobrary.librarybook.ui.LibraryBookDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class LibraryBookServiceTest {

    @Mock
    private LibraryBookRepository libraryBookRepository;

    @InjectMocks
    private LibraryBookService libraryBookService;

    private LibraryBookDto libraryBookDto;
    private LibraryBook libraryBook;

    @BeforeEach
    void setUp() {
        libraryBookDto = new LibraryBookDto("https://image.url", "제목", "작가", "출판사", "19930705", "내용 없음");
        libraryBook = new LibraryBook(
                new BookCoverUrl("https://image.url"),
                new BookInfo("제목", "작가", "출판사", "19930705", "내용 없음")
        );
        ReflectionTestUtils.setField(libraryBook, "id", 1L);
    }

    @DisplayName("save 가 정상적으로 동작한다.")
    @Test
    void saveSuccess() {
        given(libraryBookRepository.save(any(LibraryBook.class))).willReturn(libraryBook);
        given(libraryBookRepository.existsByLibraryBookInfoIsbn(any(String.class))).willReturn(false);

        LibraryBookCreateResponseDto libraryBookCreateResponseDto = libraryBookService.save(libraryBookDto);

        assertThat(libraryBookCreateResponseDto.getId()).isEqualTo(libraryBook.getId());
        assertThat(libraryBookCreateResponseDto.getMessage()).isEqualTo(libraryBook.getTitle() + " register succeed");
    }

    @DisplayName("중복된 isbn 에 대한 save 가 실패한다.")
    @Test
    void saveFailed() {
        given(libraryBookRepository.save(any(LibraryBook.class))).willReturn(libraryBook);
        given(libraryBookRepository.existsByLibraryBookInfoIsbn(any(String.class))).willReturn(true);

        assertThrows(DuplicatedLibraryBookException.class, () -> libraryBookService.save(libraryBookDto));
    }
}