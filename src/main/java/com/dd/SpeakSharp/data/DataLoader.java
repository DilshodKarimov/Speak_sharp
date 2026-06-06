package com.dd.SpeakSharp.data;

import com.dd.SpeakSharp.entity.Twister;
import com.dd.SpeakSharp.repository.TwisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataLoader {

    private final TwisterRepository twisterRepository;

    @Bean
    CommandLineRunner loadTwisters() {
        return args -> {

            if (twisterRepository.count() > 0) {
                return;
            }

            List<Twister> twisters = List.of(
                    new Twister("Карл у Клары украл кораллы"),
                    new Twister("Шла Саша по шоссе и сосала сушку"),
                    new Twister("От топота копыт пыль по полю летит"),
                    new Twister("На дворе трава, на траве дрова"),
                    new Twister("Кукушка кукушонку купила капюшон"),
                    new Twister("Ехал Грека через реку, видит Грека — в реке рак"),
                    new Twister("У четырех черепашек по четыре черепашонка"),
                    new Twister("Тридцать три корабля лавировали, лавировали да не вылавировали"),
                    new Twister("Бык тупогуб, тупогубенький бычок"),
                    new Twister("Сшит колпак не по-колпаковски"),
                    new Twister("У осы не усы, не усищи, а усики"),
                    new Twister("Лигурия регулировщик регулировал в Лигурии"),
                    new Twister("Повар Петр, повар Павел"),
                    new Twister("Король орел"),
                    new Twister("Два дровосека, два дровокола, два дроворуба"),
                    new Twister("Съел молодец тридцать три пирога"),
                    new Twister("Мышки сушек насушили"),
                    new Twister("Клара-краля кралась к Ларе"),
                    new Twister("Баран Буян залез в бурьян"),
                    new Twister("Водовоз вез воду из-под водопровода"),
                    new Twister("Петр Петрович по прозвищу Перов"),
                    new Twister("Жужжит жужелица, жужжит да кружится"),
                    new Twister("Цапля чахла, цапля сохла"),
                    new Twister("Пекарь пек калачи в печи"),
                    new Twister("Краб крабу сделал грабли"),
                    new Twister("Проворонила ворона вороненка"),
                    new Twister("Хохлатые хохотушки хохотом хохотали"),
                    new Twister("Щетинка у чушки, чешуя у щучки"),
                    new Twister("Шестнадцать шли мышей"),
                    new Twister("В поле полет Фрося просо"),
                    new Twister("Рапортовал да не дорапортовал"),
                    new Twister("Интервьюер интервента интервьюировал"),
                    new Twister("Полили ли лилию, видели ли Лидию"),
                    new Twister("Ткет ткач ткани на платки Тане"),
                    new Twister("Вахмистр с вахмистршей"),
                    new Twister("Белые бараны били в барабаны"),
                    new Twister("У перепелки и перепела пять перепелят"),
                    new Twister("Бобр добр до бобрят"),
                    new Twister("Добыл бобов бобыль"),
                    new Twister("Три сороки тараторки тараторили на горке"),
                    new Twister("Косарь Касьян косой косит косо"),
                    new Twister("Три дроворуба на трех дворах"),
                    new Twister("Говорил попугай попугаю"),
                    new Twister("Сеня вез воз сена"),
                    new Twister("Семь суток сорока старалась"),
                    new Twister("Четыре черненьких чумазеньких чертенка"),
                    new Twister("Шапкой Саша шишки сшиб"),
                    new Twister("Жили были три китайца"),
                    new Twister("Скрипят скворцы на скрипке"),
                    new Twister("Вез корабль карамель"),
                    new Twister("Краб карабкался к крабу"),
                    new Twister("Собирала Маргарита маргаритки"),
                    new Twister("Бежит лиса по шесточку"),
                    new Twister("Маланья-болтунья молоко болтала"),
                    new Twister("Три трубача трубили в трубы"),
                    new Twister("Шустрый шмель шуршит в шалфее"),
                    new Twister("У елки иголки колки"),
                    new Twister("Толя топал по болоту"),
                    new Twister("Воробей воровал варенье"),
                    new Twister("Синий-синий василек"),
                    new Twister("У ежа ежата, у ужа ужата"),
                    new Twister("Жук жужжал над абажуром"),
                    new Twister("Сыворотка из-под простокваши"),
                    new Twister("Летели лебеди с лебедятами"),
                    new Twister("Ткет паук паутину"),
                    new Twister("Пошла Поля полоть в поле"),
                    new Twister("Рыла свинья тупорыла"),
                    new Twister("Собака шла по шоссе"),
                    new Twister("Кот Кокос ловил кокос"),
                    new Twister("Мама мыла Милу мылом"),
                    new Twister("Тимошка крошит в окрошку крошки"),
                    new Twister("Кирилл кричал крикунам"),
                    new Twister("Шесть мышат в камышах шуршат"),
                    new Twister("Коля колья колол"),
                    new Twister("Слон слонялся по склону"),
                    new Twister("Таня тащит тачку"),
                    new Twister("Федя ехал на велосипеде"),
                    new Twister("Галка села на палку"),
                    new Twister("Лара играла на рояле"),
                    new Twister("Шуршат шины у машины"),
                    new Twister("Три тигра у трактира"),
                    new Twister("Саша шапкой шишки сшиб"),
                    new Twister("Кот ловил карасей"),
                    new Twister("Рома ремонтировал радиоприемник"),
                    new Twister("Тимур трубил в трубу"),
                    new Twister("Ваня варил варенье"),
                    new Twister("Лена лепила лилии"),
                    new Twister("Света свистела свирелью"),
                    new Twister("Боря бросил бревно"),
                    new Twister("Юра юлил у юлы"),
                    new Twister("Миша мешал мешки"),
                    new Twister("Дима дымил дымовухой"),
                    new Twister("Петя пилил пилой"),
                    new Twister("Рита рыла рвы"),
                    new Twister("Яша ел яичницу"),
                    new Twister("Три тропки у тростника"),
                    new Twister("Шура шила шубу"),
                    new Twister("Витя вез виноград"),
                    new Twister("Жора жарил жаркое"),
                    new Twister("Нина нанизывала нитки"),
                    new Twister("Кеша кашу кушал"),
                    new Twister("Леша ловко ловил леща")
            );

            twisterRepository.saveAll(twisters);
        };
    }
}
