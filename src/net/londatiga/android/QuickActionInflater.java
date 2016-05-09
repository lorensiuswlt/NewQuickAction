package net.londatiga.android;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;

/**
 * created at 06.09.2012 - 22:27:57<br />
 * creator: ollo<br />
 * project: TimeWolf<br />
 * $Id: $<br />
 * 
 * @author ollo<br />
 * 
 *         Inspired by the original MenuInflater, found at
 *         http://www.devdaily.com
 *         /java/jwarehouse/android/core/java/android/view
 *         /MenuInflater.java.shtml
 */
public class QuickActionInflater {

    private static final String ITEM = "item";
    private static final String ICON = "icon";
    private static final String TITLE = "title";
    private static final String ACTION_ID = "id";
    
    private Context mContext;

    public QuickActionInflater(Context context) {
        this.mContext = context;
    }

    /**
     * Load the a standard menu.xml (menuRes) and add all items to the menu.
     * @param menuRes
     * @param menu
     * 
     * Usage:
     * <code>
     * final QuickAction mQuickAction  = new QuickAction(mContext);
     *  final QuickActionInflater menuinflater = new QuickActionInflater(mContext);
     *  menuinflater.inflate(R.menu.quickaction_menu, mQuickAction);
     *  
     *  //setup the action item click listener
     *  mQuickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
     *      @Override
     *      public void onItemClick(QuickAction quickAction, int pos, int actionId) {
     *          switch (actionId) {
     *          case R.id.action_event_create:
     *              ; // do something
     *              break;
     *          }
     *       }
     *   };
     *   mQuickAction.show(view); // sometimes later (when it is actual needed)
     * </code>
     */
    public void inflate(final int menuRes, QuickAction menu) {
        XmlResourceParser parser = null;
        try {
            parser = mContext.getResources().getLayout(menuRes);
            AttributeSet attrs = Xml.asAttributeSet(parser);
            parseMenu(parser, attrs, menu);
        } catch (XmlPullParserException e) {
            throw new InflateException("Error inflating menu XML", e);
        } catch (IOException e) {
            throw new InflateException("Error inflating menu XML", e);
        } finally {
            if (parser != null)
                parser.close();
        }
    }

    private void parseMenu(XmlResourceParser xrp, AttributeSet attrs, QuickAction menu) throws XmlPullParserException,
            IOException {
        while (xrp.getEventType() != XmlPullParser.END_DOCUMENT) {
            if (xrp.getEventType() == XmlPullParser.START_TAG) {
                try {
                    handleStart(xrp, menu); // Do stuff with START_TAG
                } catch(NumberFormatException nfe) {
                    throw new IOException("wrong formated XML", nfe);
                }
            } 
            xrp.next();
        }
    }

    private void handleStart(XmlResourceParser xrp, QuickAction menu) throws NumberFormatException {
        if (xrp.getName().equals(ITEM)) {
            //Log.v("QuickAction", "attr count = " + xrp.getAttributeCount());
            String uniqueId = xrp.getAttributeValue(xrp.getAttributeNamespace(0), ACTION_ID);
            uniqueId = trimNumber(uniqueId);
            final int actionId = Integer.parseInt(uniqueId);
            
            String titleId = xrp.getAttributeValue(xrp.getAttributeNamespace(0), TITLE);
            titleId = trimNumber(titleId);
            final String title = mContext.getString(Integer.parseInt(titleId));
            
            String iconId = xrp.getAttributeValue(xrp.getAttributeNamespace(0), ICON);
            iconId = trimNumber(iconId);
            final int icon = Integer.parseInt(iconId);

            //Log.v("QuickActionInflater", "Item id=" + actionId + " " + title + " " + icon);
            menu.addActionItem(new ActionItem(actionId, title, mContext.getResources().getDrawable(icon)));
        }
    }

    /**
     * remove characters at the beginning
     * @param titleId
     * @return
     */
    private String trimNumber(String titleId) {
        while (titleId.length() > 1 && !Character.isDigit(titleId.charAt(0))) {
            titleId = titleId.substring(1);
        }
        return titleId;
    }

}
