package com.example.sistemadeponto;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistroFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView txtHorarioEntrada, txtHorarioAlmoco, txtHorarioRetorno, txtHistorico;
    private Button btnRegistrarEntrada, btnRegistrarSaida;
    private Handler handler = new Handler();
    private Runnable atualizarRelogio;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegistroFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistroFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistroFragment newInstance(String param1, String param2) {
        RegistroFragment fragment = new RegistroFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registro, container, false);

        // Conecta os elementos
        txtHorarioEntrada = view.findViewById(R.id.txtHorarioEntrada);
        txtHorarioAlmoco = view.findViewById(R.id.txtHorarioAlmoco);
        txtHorarioRetorno = view.findViewById(R.id.txtHorarioRetorno);
        txtHistorico = view.findViewById(R.id.txtHistorico);
        btnRegistrarEntrada = view.findViewById(R.id.btnRegistrarEntrada);
        btnRegistrarSaida = view.findViewById(R.id.btnRegistrarSaida);

        // Ação ao clicar em "Registrar Entrada"
        btnRegistrarEntrada.setOnClickListener(v -> {
            String horaAtual = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
            txtHorarioEntrada.setText("Entrada: " + horaAtual);
            txtHistorico.setText("Hoje: " + horaAtual + " - ...");

            DatabaseHelper dbHelper = new DatabaseHelper(getContext());
            Funcionario funcionarioLogado = FuncionarioStorage.getFuncionarioLogado();


            if (funcionarioLogado != null) {
                dbHelper.inserirStatus(funcionarioLogado.getNome(), "online");
                funcionarioLogado.setStatus("online");
                Toast.makeText(getContext(), "Status alterado para ONLINE", Toast.LENGTH_SHORT).show();
            }
        });

        // Ação ao clicar em "Registrar Saída"
        btnRegistrarSaida.setOnClickListener(v -> {
            String horaAtual = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
            txtHorarioRetorno.setText("Saída: " + horaAtual);
            txtHistorico.append("\nSaída registrada às " + horaAtual);

            DatabaseHelper dbHelper = new DatabaseHelper(getContext());
            Funcionario funcionarioLogado = FuncionarioStorage.getFuncionarioLogado();

            if (funcionarioLogado != null) {
                dbHelper.inserirStatus(funcionarioLogado.getNome(), "offline");
                funcionarioLogado.setStatus("offline");
                Toast.makeText(getContext(), "Status alterado para OFFLINE", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
    }
