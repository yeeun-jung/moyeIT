//package com.example.jangwon.androidlistview;
//
//import android.content.Context;
//import android.util.TypedValue;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import org.w3c.dom.Text;
//
//import java.util.ArrayList;
//
//
// public class MyAdapter extends BaseAdapter {
//
// ArrayList<BaseballItem> items = new ArrayList<BaseballItem>();
//
// Context mContext;
//
// public MyAdapter(Context context) {
// mContext = context;
// }
//
// @Override
// public int getCount() {
// return items.size();
// }
//
// @Override
// public Object getItem(int position) {
// return items.get(position);
// }
//
// @Override
// public long getItemId(int position) {
// return position;
// }
//
// public void addItem(BaseballItem item) {
// items.add(item);
// }
//
// public void clear() {
// items.clear();
// }
//
// @Override
// public View getView(int position, View convertView, ViewGroup parent) {
// // 화면에 보일 뷰 객체를 리턴해주는 메소드
// //        LinearLayout layout = new LinearLayout(mContext);
// // 세로 방향으로 뷰를 추가한다
// //        layout.setOrientation(LinearLayout.VERTICAL);
// // layout_width, layout_height 속성에 값을 넣어준다
// //        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
// //                LinearLayout.LayoutParams.MATCH_PARENT,
// //                LinearLayout.LayoutParams.WRAP_CONTENT);
// //
// //        TextView nameView = new TextView(mContext);
// //        nameView.setText(teams[position]);
// //        nameView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);
// //
// //        layout.addView(nameView, params);
// //
// //        TextView rankView = new TextView(mContext);
// //        rankView.setText(rank[position]);
// //        rankView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24);
// //
// //        layout.addView(rankView, params);
//
// BaseballTeamLayout layout = new BaseballTeamLayout(mContext);
//
// BaseballItem item = items.get(position);
//
// layout.setImage(item.getResId());
// layout.setNameText(item.getName());
// layout.setRankText(item.getRank());
//
// return layout;
// }
// }