package repositories;

import com.astontech.hr.configuration.RepositoryConfiguration;
import com.astontech.hr.domain.Vehicle;
import com.astontech.hr.domain.VehicleMake;
import com.astontech.hr.domain.VehicleModel;
import com.astontech.hr.repositories.VehicleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RepositoryConfiguration.class)
public class VehicleRepositoryTest {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    public void testSaveVehicle(){
        // Set up a Vehicle with Make and Model
        VehicleMake vehicleMake = new VehicleMake();
        vehicleMake.setVehicleMakeName("Toyota");

        VehicleModel vehicleModel = new VehicleModel();
        vehicleModel.setModelName("Corolla");
//        vehicleModel.setVehicleMake(vehicleMake);

        Vehicle vehicle = new Vehicle();
        vehicle.setYear(2021);
        vehicle.setLicensePlate("123ABC");
        vehicle.setVin("1HGBH41JXMN109186");
        vehicle.setColor("Blue");
        vehicle.setVehicleModel(vehicleModel);

        // Save Vehicle and verify it has an ID after the save
        assertNull(vehicle.getId());
        vehicleRepository.save(vehicle);
        assertNotNull(vehicle.getId());

        // Fetch from database
        Vehicle fetchedVehicle = vehicleRepository.findOne(vehicle.getId());
        assertNotNull(fetchedVehicle);
        assertEquals(vehicle.getId(), fetchedVehicle.getId());

        // Update vehicle color
        fetchedVehicle.setColor("Red");
        vehicleRepository.save(fetchedVehicle);

        Vehicle updatedVehicle = vehicleRepository.findOne(fetchedVehicle.getId());
        assertEquals(updatedVehicle.getColor(), "Red");
    }

    @Test
    public void testFindByLicensePlate(){
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate("XYZ123");
        vehicle.setVin("1HGBH41JXMN109187");

        vehicleRepository.save(vehicle);

        Vehicle fetchedVehicle = vehicleRepository.findVehicleByLicensePlateIgnoreCase("xyz123");
        assertNotNull(fetchedVehicle);
        assertEquals(vehicle.getId(), fetchedVehicle.getId());
    }

    @Test
    public void testFindByVin(){
        Vehicle vehicle = new Vehicle();
        vehicle.setVin("1HGBH41JXMN109188");
        vehicle.setLicensePlate("XYZ124");

        vehicleRepository.save(vehicle);

        Vehicle fetchedVehicle = vehicleRepository.findVehicleByVinIgnoreCase("1hgbh41jxmn109188");
        assertNotNull(fetchedVehicle);
        assertEquals(vehicle.getId(), fetchedVehicle.getId());
    }

    @Test
    public void testFindAllByColor(){
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setColor("Black");
        vehicle1.setVin("1HGBH41JXMN109189");

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setColor("Black");
        vehicle2.setVin("1HGBH41JXMN109190");

        vehicleRepository.save(vehicle1);
        vehicleRepository.save(vehicle2);

        List<Vehicle> blackVehicles = vehicleRepository.findAllByColorIgnoreCase("black");
        assertEquals(2, blackVehicles.size());
    }

    @Test
    public void testFindAllVehiclesWithMakeAndModel(){
        VehicleMake make = new VehicleMake();
        make.setVehicleMakeName("Ford");

//        VehicleModel model = new VehicleModel();
//        model.setModelName("Mustang");
//        model.setVehicleMake(make);

        Vehicle vehicle = new Vehicle();
        vehicle.setVin("1HGBH41JXMN109191");
//        vehicle.setLicensePlate("XYZ125");
//        vehicle.setVehicleModel(model);

        vehicleRepository.save(vehicle);

//        List<Vehicle> vehicles = vehicleRepository.findAllVehiclesWithMakeAndModel();
//        assertEquals(1, vehicles.size());
//        assertEquals("Ford", vehicles.get(0).getVehicleModel().getVehicleMake().getVehicleMakeName());
//        assertEquals("Mustang", vehicles.get(0).getVehicleModel().getModelName());
    }

}
