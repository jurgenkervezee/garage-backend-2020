package nl.jurgen.garage.service;

import nl.jurgen.garage.model.Client;
import nl.jurgen.garage.model.FileDB;
import nl.jurgen.garage.repository.ClientRepository;
import nl.jurgen.garage.repository.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileStorageService {

    @Autowired
    private FileDBRepository fileDBRepository;

    @Autowired ClientRepository clientRepository;

    public FileDB store(MultipartFile file, long clientId) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getBytes());

        Client client = null;
        if(clientRepository.existsById(clientId)){
           client = clientRepository.findById(clientId).orElse(null);
        }
        fileDB.setClient(client);

        return fileDBRepository.save(fileDB);
    }

    public FileDB getFile(String id) {

        boolean present = fileDBRepository.existsById(id);


        return fileDBRepository.findById(id).get();
    }

}
