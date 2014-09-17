package com.wondersgroup.fdpublicapp.common.custom.pullrefresh.extras;

import com.wondersgroup.fdpublicapp.common.custom.pullrefresh.PullToRefreshBase;
import com.wondersgroup.fdpublicapp.common.custom.pullrefresh.PullToRefreshBase.Mode;
import com.wondersgroup.fdpublicapp.common.custom.pullrefresh.PullToRefreshBase.OnPullEventListener;
import com.wondersgroup.fdpublicapp.common.custom.pullrefresh.PullToRefreshBase.State;
import android.content.Context;
import android.view.View;

public class SoundPullEventListener<V extends View> implements OnPullEventListener<V> {

//	private final Context mContext;
//	private final HashMap<State, Integer> mSoundMap;

//	private MediaPlayer mCurrentMediaPlayer;

	/**
	 * Constructor
	 * 
	 * @param context - Context
	 */
	public SoundPullEventListener(Context context) {
//		mContext = context;
//		mSoundMap = new HashMap<State, Integer>();
	}

	@Override
	public final void onPullEvent(PullToRefreshBase<V> refreshView, State event, Mode direction) {
//		Integer soundResIdObj = mSoundMap.get(event);
//		if (null != soundResIdObj) {
//			playSound(soundResIdObj.intValue());
//		}
	}

	/**
	 * Set the Sounds to be played when a Pull Event happens. You specify which
	 * sound plays for which events by calling this method multiple times for
	 * each event.
	 * <p/>
	 * If you've already set a sound for a certain event, and add another sound
	 * for that event, only the new sound will be played.
	 * 
	 * @param event - The event for which the sound will be played.
	 * @param resId - Resource Id of the sound file to be played (e.g.
	 *            <var>R.raw.pull_sound</var>)
	 */
	public void addSoundEvent(State event, int resId) {
//		mSoundMap.put(event, resId);
	}

	/**
	 * Clears all of the previously set sounds and events.
	 */
	public void clearSounds() {
//		mSoundMap.clear();
	}

	/**
	 * Gets the current (or last) MediaPlayer instance.
	 */
//	public MediaPlayer getCurrentMediaPlayer() {
//		return mCurrentMediaPlayer;
//		return null;
//	}

//	private void playSound(int resId) {
//		// Stop current player, if there's one playing
//		if (null != mCurrentMediaPlayer) {
//			mCurrentMediaPlayer.stop();
//			mCurrentMediaPlayer.release();
//		}
//
//		mCurrentMediaPlayer = MediaPlayer.create(mContext, resId);
//		if (null != mCurrentMediaPlayer) {
//			mCurrentMediaPlayer.start();
//		}
//	}

}
