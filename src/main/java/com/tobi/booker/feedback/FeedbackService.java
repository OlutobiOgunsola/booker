package com.tobi.booker.feedback;

import com.tobi.booker.book.Book;
import com.tobi.booker.book.BookRepository;
import com.tobi.booker.common.PageResponse;
import com.tobi.booker.exceptions.OperationNotPermittedException;
import com.tobi.booker.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final BookRepository bookRepository;
    private final FeedbackMapper feedbackMapper;
    private final FeedbackRepository feedbackRepository;

    public Integer save(FeedbackRequest request, Authentication connectedUser) {
        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(() -> new EntityNotFoundException("Book not found with ID:: " + request.bookId()));
        User user = ((User) connectedUser.getPrincipal());
        if (book.getOwner().getId().equals(user.getId())) {
            throw new OperationNotPermittedException("You cannot give feedback on your own book");
        }
        if (!book.isShareable()) {
            throw new OperationNotPermittedException("You cannot give feedback on a non shareable book");
        }
        if (book.isArchived()) {
            throw new OperationNotPermittedException("You cannot give feedback on an archived book");
        }

        Feedback feedback = feedbackMapper.toFeedback(request);
        return feedbackRepository.save(feedback).getId();
    }

    public PageResponse<FeedbackResponse> findAllFeedbacksByBook(Integer bookId, Integer page, Integer size, Authentication connectedUser) {
        Pageable pageable = PageRequest.of(page, size);
        User user = ((User) connectedUser.getPrincipal());
        Page<Feedback> feedbacks = feedbackRepository.findAllByBookId(bookId, pageable);
        List<FeedbackResponse> feedbacksResponse = feedbacks.stream()
                .map(f -> feedbackMapper.toFeedbackResponse(f, user.getId()))
                .toList();

        return new PageResponse<>(
                feedbacksResponse,
                feedbacks.getNumber(),
                feedbacks.getSize(),
                feedbacks.getTotalElements(),
                feedbacks.getTotalPages(),
                feedbacks.isFirst(),
                feedbacks.isLast()
        );
    }


}
