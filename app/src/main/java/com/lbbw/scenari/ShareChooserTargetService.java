package com.lbbw.scenari;

import android.content.ComponentName;
import android.content.IntentFilter;
import android.service.chooser.ChooserTarget;
import android.service.chooser.ChooserTargetService;

import java.util.List;

/**
 * Created by neegbeahreeves on 7/25/16.
 */
public class ShareChooserTargetService extends ChooserTargetService {
    @Override
    public List<ChooserTarget> onGetChooserTargets(ComponentName targetActivityName, IntentFilter matchedFilter) {
        return null;
    }
}
