use std::collections::HashSet;

pub fn part_1() {
    let input = include_str!(concat!(
        env!("CARGO_MANIFEST_DIR"),
        "\\src\\day_3.part_1.txt"
    ));

    let out: i32 = input
        .trim()
        .split('\n')
        .map(|item| {
            let chars = item.chars().collect::<Vec<_>>();
            let mut out = HashSet::new();
            for i in 0..chars.len() - 1 {
                for j in i + 1..chars.len() {
                    out.insert(format!("{}{}", chars[i], chars[j]).parse::<i32>().unwrap());
                }
            }

            out.into_iter().max().unwrap()
        })
        .sum();

    println!("Result Part 1: {:?}", out);
}

pub fn part_2() {
    let input = include_str!(concat!(
        env!("CARGO_MANIFEST_DIR"),
        "\\src\\day_3.part_1.txt"
    ));

    let out: i64 = input
        .trim()
        .split('\n')
        .map(|item| {
            let chars = item
                .chars()
                .map(|x| x.to_digit(10).unwrap())
                .collect::<Vec<_>>();

            let mut out = Vec::new();

            let mut index = 0;
            for i in 0..12 {
                let slice = &chars[index..chars.len() - (12 - i) + 1];
                let x = slice.iter().max().unwrap();
                index += slice.iter().position(|n| n == x).unwrap() + 1;

                out.push(x.to_string());
            }

            out.join("").parse::<i64>().unwrap()
        })
        .sum();

    println!("Result Part 1: {:?}", out);
}
