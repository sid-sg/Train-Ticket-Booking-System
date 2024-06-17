package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entities.Train;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TrainService {

    private Train train;
    private List<Train> trainList;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String trainsPath = "app/src/main/java/org/example/localDB/Trains.json";


    public TrainService() throws IOException {
        File trains = new File(trainsPath);
        trainList = objectMapper.readValue(trains, new TypeReference<List<Train>>() {});
    }

    public void saveTrainListToFile() throws IOException {
        File trainFile = new File(trainsPath);
        objectMapper.writeValue(trainFile, trainList);
    }

    public List<Train> searchTrains(String source, String destination) throws IOException {
        return trainList.stream().filter(t -> validTravel(t, source, destination)).collect(Collectors.toList());
    }

    public boolean validTravel(Train t, String source, String destination){
        List<String> stations = t.getStations();
        int sourceIndex = stations.indexOf(source.toLowerCase());
        int destinationIndex = stations.indexOf(destination.toLowerCase());
        return sourceIndex != -1 && destinationIndex != -1 && sourceIndex<destinationIndex;
    }

    public void addTrain(Train newTrain) throws IOException {
        Optional<Train>  existingTrain = trainList.stream()
                .filter(t -> newTrain.getTrainID().equals(t.getTrainID())).findFirst();

        if(existingTrain.isPresent()){
            updateTrain(newTrain);
        }
        else{
            trainList.add(newTrain);
            saveTrainListToFile();
        }
    }

    public void updateTrain(Train newTrain) throws IOException {
        OptionalInt index = IntStream.range(0,trainList.size())
                .filter( i -> trainList.get(i).getTrainID().equals( newTrain.getTrainID() )).findFirst();

        if(index.isPresent()){
            trainList.set(index.getAsInt(), newTrain);
            saveTrainListToFile();
        }
        else{
            addTrain(newTrain);
        }
    }

}
