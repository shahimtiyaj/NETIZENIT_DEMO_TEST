package com.netizen.netizenittest.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.netizen.netizenittest.Activity.SingleStudentActivity;
import com.netizen.netizenittest.Database.DatabaseHandler;
import com.netizen.netizenittest.R;
import com.netizen.netizenittest.model.StudentInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class StudentInfoAdapter extends RecyclerView.Adapter<StudentInfoAdapter.MyViewHolder> {

    private Context mContext;
    private List<StudentInfo> sInfoList;
    private DatabaseHandler db;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView SName, email, phone, blood;
        public ImageView sImage;
        public Button viewButton, editButton, deleteButton;
        private CircleImageView mProfileImageView;


        public MyViewHolder(View view) {
            super(view);
            mContext = itemView.getContext();
            db = new DatabaseHandler(mContext);

            SName = (TextView) view.findViewById(R.id.sname);
            email = (TextView) view.findViewById(R.id.email);
            phone = (TextView) view.findViewById(R.id.phone);
            blood = (TextView) view.findViewById(R.id.blood);
            viewButton = (Button) view.findViewById(R.id.view_bt);
            editButton = (Button) view.findViewById(R.id.edit_bt);
            deleteButton = (Button) view.findViewById(R.id.delete_bt);

            //  sImage = (ImageView) view.findViewById(R.id.show_image);
            mProfileImageView = (CircleImageView) view.findViewById(R.id.show_image);
        }
    }

    public StudentInfoAdapter(Context mContext, List<StudentInfo> itemList) {
        this.mContext = mContext;
        this.sInfoList = itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_info_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        try {
            StudentInfo studentInfo = sInfoList.get(position);
            holder.SName.setText(studentInfo.getSName());
            holder.email.setText(studentInfo.getSEmail());
            holder.phone.setText("Contact : " + studentInfo.getSPhone());
            holder.blood.setText("B.Group : " + studentInfo.getSBloodGroup());
            holder.mProfileImageView.setImageBitmap(convertToBitmap(studentInfo.getSImage()));
        } catch (Exception e) {
            e.printStackTrace();
        }


        holder.viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(mContext, SingleStudentActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("simage", sInfoList.get(position).getSImage());
                    intent.putExtra("sname", sInfoList.get(position).getSName());
                    intent.putExtra("birtdate", sInfoList.get(position).getSBirthDate());
                    intent.putExtra("email", sInfoList.get(position).getSEmail());
                    intent.putExtra("gender", sInfoList.get(position).getSGender());
                    intent.putExtra("blood", sInfoList.get(position).getSBloodGroup());

                    intent.putExtra("phone", sInfoList.get(position).getSPhone());
                    intent.putExtra("area", sInfoList.get(position).getSArea());
                    intent.putExtra("city", sInfoList.get(position).getSCity());
                    intent.putExtra("postcode", sInfoList.get(position).getSPinCode());
                    intent.putExtra("agree", sInfoList.get(position).getSAgree());
                    intent.putExtra("contactperson", sInfoList.get(position).getSContactPersion());
                    intent.putExtra("class", sInfoList.get(position).getSClass());
                    intent.putExtra("session", sInfoList.get(position).getSSession());
                    // intent.putExtra("shouse", sInfoList.get("2011");

                    mContext.startActivity(intent);
                    ((Activity) mContext).finish();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Log.d("Large Image", "Too Large Image");
                }
            }
        });

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Toast.makeText(mContext, "Profile Updated OnClick", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Log.d("Large Image", "Too Large Image");
                }
            }
        });
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    deleteItemFromList(view, position);
                    //delete(position);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Log.d("Large Image", "Too Large Image");
                }
            }
        });
    }

    // confirmation dialog box to delete an unit
    private void deleteItemFromList(View v, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Delete Record");
        builder.setIcon(R.drawable.ic_delete_black_24dp);
        builder.setMessage("Are You Sure You Want to Delete this Student Information?")
                .setCancelable(false)
                .setPositiveButton("CONFIRM",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                delete(position);
                            }
                        })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        builder.show();

    }
    private Bitmap convertToBitmap(byte[] b) {
        return BitmapFactory.decodeByteArray(b, 0, b.length);
    }

    @Override
    public int getItemCount() {
        return sInfoList.size();
    }


    public void delete(int position) {
        final int deleteId;
        final StudentInfo messages = (StudentInfo) sInfoList.get(position);

        deleteId = messages.getID();
        db.deleteStudentInfo(deleteId);
        sInfoList.remove(position);
        notifyItemRemoved(position);
    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public Bitmap decodeFile(File f) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            // The new size we want to scale to
            final int REQUIRED_SIZE = 70;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {
        }
        return null;
    }

}
