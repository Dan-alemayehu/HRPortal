//package services;
//
//import com.astontech.hr.domain.Vehicle;
//import com.astontech.hr.domain.VehicleMake;
//import com.astontech.hr.domain.VehicleModel;
//import com.astontech.hr.repositories.VehicleRepository;
//import com.astontech.hr.services.VehicleService;
//import com.astontech.hr.services.impl.VehicleServiceImpl;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.mockito.Mockito.*;
//
//public class VehicleServiceTest {
//
//    @Mock
//    private VehicleRepository vehicleRepository;
//
//    @InjectMocks
//    private VehicleServiceImpl vehicleService;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testGetAllVehicles() {
//        Vehicle vehicle1 = new Vehicle();
//        vehicle1.setVin("VIN123");
//
//        Vehicle vehicle2 = new Vehicle();
//        vehicle2.setVin("VIN456");
//
//        when(vehicleRepository.findAll()).thenReturn(Arrays.asList(vehicle1, vehicle2));
//
//        Iterable<Vehicle> vehicles = vehicleService.getAllVehicles();
//        List<Vehicle> vehicleList = (List<Vehicle>) vehicles;
//
//        assertNotNull(vehicles);
//        assertEquals(2, vehicleList.size());
//        verify(vehicleRepository, times(1)).findAll();
//    }
//
//    @Test
//    public void testGetVehicleById() {
//        Vehicle vehicle = new Vehicle();
//        vehicle.setId(1);
//        vehicle.setVin("VIN123");
//
//        when(vehicleRepository.findVehicleById(1)).thenReturn(vehicle);
//
//        Vehicle fetchedVehicle = vehicleService.getVehicleById(1);
//        assertNotNull(fetchedVehicle);
//        assertEquals("VIN123", fetchedVehicle.getVin());
//        verify(vehicleRepository, times(1)).findVehicleById(1);
//    }
//
//    @Test
//    public void testSaveVehicle() {
//        Vehicle vehicle = new Vehicle();
//        vehicle.setVin("VIN123");
//
//        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle);
//
//        String result = vehicleService.saveVehicle(vehicle);
//        assertEquals("Success", result);
//        verify(vehicleRepository, times(1)).save(vehicle);
//    }
//
//    @Test
//    public void testDeleteVehicleById() {
//        vehicleService.deleteVehicleById(1);
//        verify(vehicleRepository, times(1)).deleteVehicleById(1);
//    }
//
//    @Test
//    public void testFindVehicleByLicensePlateIgnoreCase() {
//        Vehicle vehicle = new Vehicle();
//        vehicle.setLicensePlate("ABC123");
//
//        when(vehicleRepository.findVehicleByLicensePlateIgnoreCase("abc123")).thenReturn(vehicle);
//
//        Vehicle fetchedVehicle = vehicleService.findVehicleByLicensePlateIgnoreCase("abc123");
//        assertNotNull(fetchedVehicle);
//        assertEquals("ABC123", fetchedVehicle.getLicensePlate());
//        verify(vehicleRepository, times(1)).findVehicleByLicensePlateIgnoreCase("abc123");
//    }
//
//    @Test
//    public void testFindVehicleByVinIgnoreCase() {
//        Vehicle vehicle = new Vehicle();
//        vehicle.setVin("VIN123");
//    }
//}

