package com.main.timeshareexchangeplatform_backend.service.impl;

import com.main.timeshareexchangeplatform_backend.converter.BookingConverter;
import com.main.timeshareexchangeplatform_backend.dto.BookingModel;
import com.main.timeshareexchangeplatform_backend.entity.Booking;
import com.main.timeshareexchangeplatform_backend.repository.BookingRepository;
import com.main.timeshareexchangeplatform_backend.service.IBookingService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookingImpl implements IBookingService {
    @Autowired
    BookingConverter bookingConverter;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    IBookingService bookingService;


//@Override
//    public BookingModel addBooking(BookingModel bookingModel) {
//
//    Booking booking = bookingConverter.toEntity(bookingModel);
//
//    Booking existingBooking = bookingRepository.getReferenceById(booking.getBooking_id());
//
//    BookingModel result = bookingConverter.toDTO(booking);
//    return result;
//}

//    @Override
//    public BookingModel addBooking(BookingModel bookingModel) {
//
//        // Extract the timeshare ID from the booking model
//        Integer timeshareId = bookingModel.getTimeshare_id();
//
//        // Check if the timeshare is already booked
//        boolean timeshareAlreadyBooked = bookingRepository.isTimeshareBooked(bookingModel.getUser_id(), timeshareId);
//
//        if (!timeshareAlreadyBooked) {
//            // Convert BookingModel to Booking entity
//            Booking booking = bookingConverter.toEntity(bookingModel);
//
//            // Check if the booking already exists in the database
//            Booking existingBooking = bookingRepository.getReferenceById(bookingModel.getBooking_id());
//
//            if (existingBooking == null) {
//                // Save the booking to the database
////                booking.setBooking_id(booking.getBooking_id());
//                booking.setCreate_date(LocalDate.now());
//                booking.setStatus(false); // Default to booked
//                booking = bookingRepository.save(booking);
//                bookingConverter.toDTO(booking);
//
//                // Create a list to store information about the Timeshare_Booking added
//                List<Timeshare_Booking> addedTimeshareBookings = new ArrayList<>();
//
//                // Add the timeshare to the booking through Timeshare_Booking
//                Timeshare_Booking timeshareBooking = new Timeshare_Booking();
//                Timeshare timeshare = timeshareRepository.findById(timeshareId).orElse(null);
//
//                if (timeshare != null) {
//                    timeshareBooking.setTimeshare(timeshare);
//                    timeshareBooking.setBooking(booking);
//                    timeshareBookingRepository.save(timeshareBooking);
//                    addedTimeshareBookings.add(timeshareBooking);
//                }
//
//                // Convert the list of Timeshare_Booking to a list of Timeshare_BookingModel
//                List<Timeshare_BookingModel> timeshareBookingModels = addedTimeshareBookings.stream()
//                        .map(timeshareBookingConverter::toDTO)
//                        .collect(Collectors.toList());
//
//                // Return BookingModel along with the list of Timeshare_BookingModel
//                BookingModel result = bookingConverter.toDTO(booking);
//                result.setTimeshareBookings(timeshareBookingModels);
//                return result;
//            }
//
//            return null;
//        } else {
//            return null; // Timeshare is already booked
//        }
//    }

@Override
    public BookingModel addBooking(BookingModel bookingModel) {

        // Extract the timeshare ID from the booking model
        Integer timeshareId = bookingModel.getTimeshare_id();

        // Check if the timeshare is already booked
        boolean timeshareAlreadyBooked = bookingService.isTimeshareBooked(timeshareId);

        if (!timeshareAlreadyBooked) {
            // Convert BookingModel to Booking entity
            Booking booking = bookingConverter.toEntity(bookingModel);

            // Check if the booking already exists in the database
            Booking existingBooking = bookingRepository.getReferenceById(bookingModel.getBooking_id());

            if (existingBooking == null) {
                // Save the booking to the database
//                booking.setBookingCode(booking.getBookingCode());
                booking.setCreate_date(LocalDate.now());
                booking.setStatus(false); // Default to booked
                booking = bookingRepository.save(booking);
                bookingConverter.toDTO(booking);

//                // Create a Timeshare_Booking entry for the booking
//                Timeshare_Booking timeshareBooking = new Timeshare_Booking();
//                Timeshare timeshare = timeshareRepository.findById(timeshareId).orElse(null);
//
//                if (timeshare != null) {
//                    timeshareBooking.setTimeshare(timeshare);
//                    timeshareBooking.setBooking(booking);
//                    timeshareBookingRepository.save(timeshareBooking);
//                }

                // Return the BookingModel
                return bookingConverter.toDTO(booking);
            }

            return null; // Booking with the same code already exists
        } else {
            return null; // Timeshare is already booked
        }
    }

    @PersistenceContext
    private EntityManager entityManager;

    public boolean isTimeshareBooked(Integer timeshareId) {
        // Check if there is any existing booking for the specified timeshare using JPQL
        Query query = entityManager.createQuery("SELECT COUNT(tb) FROM Booking tb WHERE tb.timeshare.id = :timeshareId");
        query.setParameter("timeshareId", timeshareId);

        Long count = (Long) query.getSingleResult();

        return count > 0;
    }



}
