package sg.edu.nus.iss.ft08.siacdm;

import android.app.Application;
import android.content.Intent;

import sg.edu.nus.iss.ft08.siacdm.home.SummaryDelegate;
import sg.edu.nus.iss.ft08.siacdm.model.Summary;

public class MainController implements SiaCdmBaseController {
    private static Application app = null;
    private MainActivity mainActivity;

    public static Application getApp() {
        return app;
    }

    public static void setApp(Application app) {
        MainController.app = app;
    }

    public static void displayScreen(Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        app.startActivity(intent);
    }

    public void startUseCase() {
        Intent intent = new Intent(MainController.getApp(), MainActivity.class);
        MainController.displayScreen(intent);
    }

    public void onDisplay(MainActivity activity) {
        this.mainActivity = activity;
        SummaryDelegate delegate = new SummaryDelegate(this);
        delegate.execute();
    }

    public void showDashboardItems(Summary summary) {
        mainActivity.showDashboardItems(summary);
    }
}
