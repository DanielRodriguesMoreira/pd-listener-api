package pt.drmoreira.pd_listener_api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
public class ClientController {

	@CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/clients")
    public ResponseEntity<List<String>> helloWorld() {
        return this.getFromFile();
    }

    private ResponseEntity<List<String>> getFromFile() {
        String fileName = "C:/Users/drmor/Documents/PD/Aulas Práticas/clients_connected.txt";
        String line;
        List<String> ips = new ArrayList<>();

        try(BufferedReader inFile = new BufferedReader(new FileReader(fileName))){
            while((line = inFile.readLine())!=null) {
                line = line.trim();

                if(line.isEmpty()){
                    continue;
                }

                try {
                    Scanner scan = new Scanner(line);
                    ips.add(scan.next());
                } catch(Exception e){
                    System.err.print("> Entrada incorrecta no ficheiro ");
                }
            }
        } catch (FileNotFoundException e){
            System.err.println();
            System.err.println("Impossibilidade de abrir o ficheiro: " + fileName + "\n\t" + e);
        } catch (IOException e){
            System.err.println();
            System.err.println(e);
        }

        return ResponseEntity.ok(ips);
    }
}
