package com.example.sistemadeponto;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificacoesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificacoesFragment extends Fragment {

    private RecyclerView recyclerNotificacoes;
    private NotificacaoAdapter notificacaoAdapter;
    private List<Notificacao> listaNotificacoes;
    private DatabaseHelper dbHelper;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NotificacoesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificacoesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificacoesFragment newInstance(String param1, String param2) {
        NotificacoesFragment fragment = new NotificacoesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notificacoes, container, false);

        recyclerNotificacoes = view.findViewById(R.id.recyclerNotificacoes);
        recyclerNotificacoes.setLayoutManager(new LinearLayoutManager(getContext()));

        dbHelper = new DatabaseHelper(getContext());
        listaNotificacoes = dbHelper.getTodasNotificacoes();

        notificacaoAdapter = new NotificacaoAdapter(listaNotificacoes);
        recyclerNotificacoes.setAdapter(notificacaoAdapter);

        return view;
    }
}