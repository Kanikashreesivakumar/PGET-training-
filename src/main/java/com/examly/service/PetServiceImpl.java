package com.examly.service;

import com.examly.entity.Pet;
import com.examly.util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetServiceImpl implements PetService {

    private static final String INSERT_SQL =
            "INSERT INTO pets (name, type, breed, age, adopted) VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE_SQL =
            "UPDATE pets SET name = ?, type = ?, breed = ?, age = ?, adopted = ? WHERE petid = ?";

    private static final String DELETE_SQL =
            "DELETE FROM pets WHERE petid = ?";

    private static final String SELECT_BY_ID_SQL =
            "SELECT petid, name, type, breed, age, adopted FROM pets WHERE petid = ?";

    private static final String SELECT_ALL_SQL =
            "SELECT petid, name, type, breed, age, adopted FROM pets ORDER BY petid";

    private static final String SEARCH_BY_NAME_SQL =
            "SELECT petid, name, type, breed, age, adopted FROM pets WHERE name LIKE ? ORDER BY petid";

    private static final String FILTER_BY_TYPE_SQL =
            "SELECT petid, name, type, breed, age, adopted FROM pets WHERE type = ? ORDER BY petid";

    @Override
    public String addPet(Pet pet) {
        String validation = validateForInsert(pet);
        if (validation != null) {
            return validation;
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(INSERT_SQL);
            preparedStatement.setString(1, pet.getName().trim());
            preparedStatement.setString(2, pet.getType().trim());
            preparedStatement.setString(3, pet.getBreed().trim());
            preparedStatement.setInt(4, pet.getAge());
            preparedStatement.setBoolean(5, pet.isAdopted());

            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                return "Pet added successfully!";
            }
            return "Error adding pet.";
        } catch (SQLException e) {
            return "Error adding pet.";
        } finally {
            DBConnectionUtil.closeQuietly(preparedStatement);
            DBConnectionUtil.closeQuietly(connection);
        }
    }

    @Override
    public String updatePet(Pet pet) {
        String validation = validateForUpdate(pet);
        if (validation != null) {
            return validation;
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_SQL);
            preparedStatement.setString(1, pet.getName().trim());
            preparedStatement.setString(2, pet.getType().trim());
            preparedStatement.setString(3, pet.getBreed().trim());
            preparedStatement.setInt(4, pet.getAge());
            preparedStatement.setBoolean(5, pet.isAdopted());
            preparedStatement.setInt(6, pet.getPetid());

            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                return "Pet updated successfully!";
            }
            return "Pet not found.";
        } catch (SQLException e) {
            return "Error updating pet.";
        } finally {
            DBConnectionUtil.closeQuietly(preparedStatement);
            DBConnectionUtil.closeQuietly(connection);
        }
    }

    @Override
    public String deletePet(int petid) {
        if (petid <= 0) {
            return "Invalid pet ID.";
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_SQL);
            preparedStatement.setInt(1, petid);

            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                return "Pet deleted successfully!";
            }
            return "Pet not found.";
        } catch (SQLException e) {
            return "Error deleting pet.";
        } finally {
            DBConnectionUtil.closeQuietly(preparedStatement);
            DBConnectionUtil.closeQuietly(connection);
        }
    }

    @Override
    public Pet getPetById(int petid) {
        if (petid <= 0) {
            return null;
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL);
            preparedStatement.setInt(1, petid);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return mapRow(resultSet);
            }
            return null;
        } catch (SQLException e) {
            return null;
        } finally {
            DBConnectionUtil.closeQuietly(resultSet);
            DBConnectionUtil.closeQuietly(preparedStatement);
            DBConnectionUtil.closeQuietly(connection);
        }
    }

    @Override
    public List<Pet> getAllPets() {
        List<Pet> pets = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_SQL);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                pets.add(mapRow(resultSet));
            }
            return pets;
        } catch (SQLException e) {
            return pets;
        } finally {
            DBConnectionUtil.closeQuietly(resultSet);
            DBConnectionUtil.closeQuietly(preparedStatement);
            DBConnectionUtil.closeQuietly(connection);
        }
    }

    @Override
    public List<Pet> searchByName(String name) {
        List<Pet> pets = new ArrayList<>();
        if (name == null || name.trim().isEmpty()) {
            return pets;
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(SEARCH_BY_NAME_SQL);
            preparedStatement.setString(1, "%" + name.trim() + "%");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                pets.add(mapRow(resultSet));
            }
            return pets;
        } catch (SQLException e) {
            return pets;
        } finally {
            DBConnectionUtil.closeQuietly(resultSet);
            DBConnectionUtil.closeQuietly(preparedStatement);
            DBConnectionUtil.closeQuietly(connection);
        }
    }

    @Override
    public List<Pet> filterByType(String type) {
        List<Pet> pets = new ArrayList<>();
        if (type == null || type.trim().isEmpty()) {
            return pets;
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(FILTER_BY_TYPE_SQL);
            preparedStatement.setString(1, type.trim());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                pets.add(mapRow(resultSet));
            }
            return pets;
        } catch (SQLException e) {
            return pets;
        } finally {
            DBConnectionUtil.closeQuietly(resultSet);
            DBConnectionUtil.closeQuietly(preparedStatement);
            DBConnectionUtil.closeQuietly(connection);
        }
    }

    private Pet mapRow(ResultSet resultSet) throws SQLException {
        Pet pet = new Pet();
        pet.setPetid(resultSet.getInt("petid"));
        pet.setName(resultSet.getString("name"));
        pet.setType(resultSet.getString("type"));
        pet.setBreed(resultSet.getString("breed"));
        pet.setAge(resultSet.getInt("age"));
        pet.setAdopted(resultSet.getBoolean("adopted"));
        return pet;
    }

    private String validateForInsert(Pet pet) {
        if (pet == null) {
            return "Invalid pet details.";
        }
        if (pet.getName() == null || pet.getName().trim().isEmpty()) {
            return "Name cannot be empty.";
        }
        if (pet.getType() == null || pet.getType().trim().isEmpty()) {
            return "Type cannot be empty.";
        }
        if (pet.getBreed() == null || pet.getBreed().trim().isEmpty()) {
            return "Breed cannot be empty.";
        }
        if (pet.getAge() <= 0) {
            return "Age must be a positive number.";
        }
        return null;
    }

    private String validateForUpdate(Pet pet) {
        if (pet == null) {
            return "Invalid pet details.";
        }
        if (pet.getPetid() <= 0) {
            return "Invalid pet ID.";
        }
        return validateForInsert(pet);
    }
}
