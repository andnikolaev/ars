package ru.rsreu.ars.controllers;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.stage.FileChooser;
import ru.rsreu.ars.core.ARSModel;
import ru.rsreu.ars.core.RtfReportWriter;
import ru.rsreu.ars.core.TreeHandler;
import ru.rsreu.ars.core.beans.Report;
import ru.rsreu.ars.core.beans.UserInformation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Тема ВКР: Разработка программной системы автоматизации составления отчетов по практическим заданиям
 * Выполнил: Николаев А.И. 09.03.04 гр.4413
 * Руководитель: Пруцков А.В., доктор технических наук, профессор каф. ВПМ
 * Средства разработки: Java, IntelliJ IDEA
 * Назанчение: Формирование отчетов и проверка качества кода
 * Дата разработки: 04.04.2018
 */
public class ARSController {

    @FXML
    Label labelFileName;
    @FXML
    Label labelTemplate;
    @FXML
    Label labelConfiguration;
    @FXML
    TextArea checkstyleMessage;
    @FXML
    TreeView filesTreeView;


    private ARSModel model = new ARSModel();
    private TreeHandler treeHandler;

    /***
     * Получение архива проекта
     * @param event Событие
     */
    @FXML
    private void getFile(ActionEvent event) {
        if (!checkTemplateAndConfigurationFileInputs()) {
            return;
        }
        Node node = (Node) event.getSource();
        FileChooser fileChooser = FileChooserConfigurationFactory.makeProjectFileChooser();
        File file = fileChooser.showOpenDialog(node.getScene().getWindow());
        if (file != null) {
            model.setFile(file);
            labelFileName.setText(file.getPath());
            //Generate tree
            String unzipDirectory = model.unzipFile(file);
            //Checkstyle
            try {
                checkstyleMessage.setText(model.runChecks());
            } catch (FileNotFoundException | CheckstyleException e) {
                checkstyleMessage.setText(e.getMessage() + "\n" + e.getCause().getMessage());
            }


            treeHandler = new TreeHandler(filesTreeView, unzipDirectory);
            treeHandler.createAllTree();
            ARSModel.deleteDirectory(new File(model.getUnzipDirectory(file.getName())));
        }
    }

    /***
     * Загрузка шаблона из интернета
     * @param event событие на форме
     */
    @FXML
    private void downloadTemplateFromInternet(ActionEvent event){
        DownloadFileController downloadFileController = new DownloadFileController();
        TextField textField = new TextField();
        while (textField != null && !downloadFileController.validate(textField)) {
            textField = downloadFileController.show("url шаблона: ","Загрузка шаблона отчета", "Загрузить шаблон");
        }

    }
    @FXML
    private void downloadConfigurationFromInternet(ActionEvent event){
        DownloadFileController downloadFileController = new DownloadFileController();
        TextField textField = new TextField();
        while (textField != null && !downloadFileController.validate(textField)) {
            textField = downloadFileController.show("url конфигурации checkstyle: ","Загрузка конфигурации checkstyle","Загрузить конф. checkstyle");
        }

    }

    /***
     * Выбор конфигурации
     * @param event событие формы
     */
    @FXML
    private void chooseConfiguration(ActionEvent event) {
        Node node = (Node) event.getSource();
        FileChooser fileChooser = FileChooserConfigurationFactory.makeCheckstyleConfigurationFileChooser();
        File file = fileChooser.showOpenDialog(node.getScene().getWindow());
        if (file != null) {
            model.setConfiguration(file);
            labelConfiguration.setText(file.getPath());
        }
    }

    /***
     * Выбор шаблона для отчета
     * @param event событие формы
     */
    @FXML
    private void chooseTemplate(ActionEvent event) {
        Node node = (Node) event.getSource();
        FileChooser fileChooser = FileChooserConfigurationFactory.makeTemplateFileChooser();
        File file = fileChooser.showOpenDialog(node.getScene().getWindow());
        if (file != null) {
            model.setTemplateForReport(file);
            labelTemplate.setText(file.getPath());
        }
    }

    /***
     * Формирование отчета
     */
    @FXML
    private void processFile() {
        if (!checkAllFileInputs()) {
            return;
        }
        Map<String, String> identifiersWithType = null;

        try {
            identifiersWithType = model.getAllIdentifiersFromTemplate();
            System.out.println(identifiersWithType);
        } catch (IOException e) {
            AlertController.showEmptyTemplateFileAlert();
        }
        Map<String, String> identifiersWithText = model.fillIdentifiersWithText(identifiersWithType.keySet());
        UserInformationController userInformationController = new UserInformationController(identifiersWithType, identifiersWithText);
        Map<String, UserInformation> userInformation = new HashMap<>();
        while (userInformation != null && !userInformationController.checkUserInformation(userInformation)) {
            userInformation = userInformationController.show();
        }
        if (userInformation != null) {

            List<File> selectedFiles = treeHandler.getAllSelected(filesTreeView);
            if (!checkSelectedFiles(selectedFiles)) {
                AlertController.showNonSelectFileForListingAlert();
                return;
            }
            model.setFilesForListing(selectedFiles);
            model.generateReport(new Report(userInformation, checkstyleMessage.getText()), new RtfReportWriter());

        }

    }

    /***
     * Проверка выбранных файлов в иерархии файлов и папок
     * @param selectedFiles Выбиранные файлы
     * @return Выбранные файлы прошли проверку или нет
     */
    private boolean checkSelectedFiles(List<File> selectedFiles) {
        if (selectedFiles.size() > 0) {
            return true;
        }
        return false;
    }

    /***
     * Проверка всех полей ввода
     * @return Все поля заполнены верно или нет
     */
    private boolean checkAllFileInputs() {
        boolean result = true;
        if (labelTemplate.getText().isEmpty()) {
            AlertController.showEmptyTemplateLinkAlert();
            result = false;
        } else if (labelConfiguration.getText().isEmpty()) {
            AlertController.showEmptyCheckstyleConfigurationLinkAlert();
            result = false;
        } else if (labelFileName.getText().isEmpty()) {
            AlertController.showEmptySourceFileLinkAlert();
            result = false;
        }
        return result;
    }

    /***
     * Проверка загрузки шаблона и конфигурационного файла checkstyle
     * @return
     */
    private boolean checkTemplateAndConfigurationFileInputs() {
        boolean result = true;
        if (labelTemplate.getText().isEmpty()) {
            AlertController.showEmptyTemplateLinkAlert();
            result = false;
        } else if (labelConfiguration.getText().isEmpty()) {
            AlertController.showEmptyCheckstyleConfigurationLinkAlert();
            result = false;
        }
        return result;
    }
}