package app;

import javax.swing.JFrame;


public class Main {
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addSplashScreenView()
                .addChooseOptionsView()
                .addDisplayOptionsView()
                .addDisplayResultsView()
                .addStartAppUseCase()
                .addChooseOptionsUseCase()
                .addFindShortestPathUseCase()
                .build();
        application.pack();
        application.setVisible(true);
        application.setResizable(false);
    }
}