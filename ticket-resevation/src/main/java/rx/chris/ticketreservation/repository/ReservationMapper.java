package rx.chris.ticketreservation.repository;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import rx.chris.ticketreservation.model.Reservation;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReservationMapper {

    Reservation toReservation(ReservationEntity entity);
}
