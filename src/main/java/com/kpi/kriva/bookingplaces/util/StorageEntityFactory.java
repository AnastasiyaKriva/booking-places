package com.kpi.kriva.bookingplaces.util;

import com.kpi.kriva.bookingplaces.entity.LocationEntity;
import com.kpi.kriva.bookingplaces.entity.RoomEntity;
import com.kpi.kriva.bookingplaces.entity.StorageEntity;
import com.kpi.kriva.bookingplaces.model.Location;
import com.kpi.kriva.bookingplaces.model.Room;
import com.kpi.kriva.bookingplaces.model.Storage;
import com.kpi.kriva.bookingplaces.model.dto.ManagerStorageDTO;

public final class StorageEntityFactory {

    public StorageEntityFactory() {}

    public static StorageEntity create(ManagerStorageDTO managerStorageDTO) {
        StorageEntity storage = new StorageEntity();
        storage.setName(managerStorageDTO.getName());
        storage.setPrice(managerStorageDTO.getPrice());
        storage.setDate(managerStorageDTO.getDate());
        storage.setStatus(managerStorageDTO.getStatus());
        storage.setLocation(getLocationEntity(managerStorageDTO));
        storage.setRoom(getRoomEntity(managerStorageDTO));
        return storage;
    }

    private static LocationEntity getLocationEntity(ManagerStorageDTO managerStorageDTO) {
        LocationEntity location = new LocationEntity();
        location.setCountry(managerStorageDTO.getCounty());
        location.setCity(managerStorageDTO.getCity());
        location.setStreet(managerStorageDTO.getStreet());
        location.setBuild(managerStorageDTO.getBuild());
        return location;
    }

    private static RoomEntity getRoomEntity(ManagerStorageDTO managerStorageDTO) {
        RoomEntity room = new RoomEntity();
        room.setName(managerStorageDTO.getRoomName());
        room.setNumberRows(managerStorageDTO.getNumberRows());
        room.setNumberSeats(managerStorageDTO.getNumberSeats());
        return room;
    }

    public static StorageEntity create(Storage storage) {
        return getStorageEntity(storage);
    }

    private static StorageEntity getStorageEntity(Storage storage) {
        StorageEntity storageEntity = new StorageEntity();
        storageEntity.setName(storage.getName());
        storageEntity.setDate(storage.getDate());
        storageEntity.setLocation(getLocationEntity(storage));
        storageEntity.setStatus(storage.getStatus());
        storageEntity.setPrice(storage.getPrice());
        storageEntity.setRoom(getRoomEntity(storage));
        return storageEntity;
    }

    private static RoomEntity getRoomEntity(Storage storage) {
        RoomEntity roomEntity = new RoomEntity();
        Room room = storage.getRoom();
        roomEntity.setName(room.getName());
        roomEntity.setNumberRows(room.getNumberRows());
        roomEntity.setNumberSeats(room.getNumberSeats());
        return roomEntity;
    }

    private static LocationEntity getLocationEntity(Storage storage) {
        LocationEntity locationEntity = new LocationEntity();
        Location location = storage.getLocation();
        locationEntity.setCountry(location.getCounty());
        locationEntity.setCity(location.getCity());
        locationEntity.setStreet(location.getStreet());
        locationEntity.setBuild(location.getBuild());
        return locationEntity;
    }

}
