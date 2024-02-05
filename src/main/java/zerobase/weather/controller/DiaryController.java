package zerobase.weather.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }
    @ApiOperation(value = "날씨&일기 생성", notes =
            "날씨는 오늘 날짜의 날씨 데이터를 갖고 옵니다. \n" +
            "과거 날씨는 현재 날씨로 대체됩니다. \n" +
                    "일기 텍스트와 날씨 데이터를 DB에 저장합니다.")
    @PostMapping("/create/diary")
    void createDiary(
            @RequestParam(value = "date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @ApiParam(value = "날짜 형식 : yyyy-MM-dd",
                    example = "2020-02-02")
            LocalDate date,
            @RequestBody
            @ApiParam(value = "일기작성", example = "오늘 날씨 좋네!")
            String text){
        diaryService.createDiary(date, text);
    }
    @ApiOperation(value = "날씨&일기 조회", notes =
        "특정 날짜의 날씨&일기를 조회합니다."
    )
    @GetMapping("/read/diary")
    List<Diary> readDiary(
            @RequestParam(value = "date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    @ApiParam(value = "날짜 형식 : yyyy-MM-dd",
                            example = "2020-02-02")
            LocalDate date) {
        return diaryService.readDiary(date);
    }
    @ApiOperation(value = "날씨&일기 기간 조회", notes =
            "특정 기간의 날씨&일기를 조회합니다."
    )
    @GetMapping("/read/diaries")
    List<Diary> readDiaies(
            @RequestParam(value = "startDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @ApiParam(value = "조회 시작 날짜 : yyyy-MM-dd",
                    example = "2020-02-02")
            LocalDate startDate,
            @RequestParam(value = "endDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @ApiParam(value = "조회 마지막 날짜 : yyyy-MM-dd",
                    example = "2020-02-03")
            LocalDate endDate) {
        return diaryService.readDiaries(startDate, endDate);
    }
    @ApiOperation(value = "특정 날짜 날씨&일기 수정", notes =
            "특정 날짜의 날씨&일기를 수정합니다."
    )
    @PutMapping("/update/diary")
    void updateDiary(
            @RequestParam(value = "date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @ApiParam(value = "날짜 형식 : yyyy-MM-dd",
                    example = "2020-02-02")
            LocalDate date,
            @ApiParam(value = "일기수정",
                    example = "오늘 날씨 좋은줄 알았는데 아니네?!")
            @RequestBody String text
    ) {
        diaryService.updateDiary(date, text);
    }
    @ApiOperation(value = "날씨&일기 특정 날짜 전부 삭제", notes =
    "특정 날짜에 해당하는 날씨&일기를 전부 삭제합니다.")
    @DeleteMapping("/delete/diary")
    void deleteDiary(
            @RequestParam(value = "date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @ApiParam(value = "날짜 형식 : yyyy-MM-dd",
                    example = "2020-02-02")
            LocalDate date){
        diaryService.deleteDiary(date);
    }

}
